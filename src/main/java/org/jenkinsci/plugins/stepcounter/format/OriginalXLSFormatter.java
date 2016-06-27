package org.jenkinsci.plugins.stepcounter.format;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.seasar.fisshplate.template.FPTemplate;

import jp.sf.amateras.stepcounter.CategoryStepDto;
import jp.sf.amateras.stepcounter.CountResult;
import jp.sf.amateras.stepcounter.Util;
import jp.sf.amateras.stepcounter.format.ExcelFormatter;
import jp.sf.amateras.stepcounter.format.ResultFormatter;

public class OriginalXLSFormatter extends ExcelFormatter implements ResultFormatter {
	public byte[] format(CountResult[] result) {
		try {
			InputStream in = OriginalXLSFormatter.class.getResourceAsStream("OriginalExcelFormatter.xls");

			long totalStep = 0;
			long totalNone = 0;
			long totalComment = 0;

			List<CategoryStepDto> categories = new ArrayList<CategoryStepDto>();
			CategoryStepDto nonCategory = new CategoryStepDto();
			nonCategory.setCategory("");
			boolean useNonCategory = false;
			for (CountResult resultDto : result) {
				CategoryStepDto categoryDto = null;
				if (resultDto.getCategory() == null || "".equals(resultDto.getCategory())) {
					categoryDto = nonCategory;
					useNonCategory = true;
				} else {
					categoryDto = getCategoryDto(categories, resultDto.getCategory());
				}
				categoryDto.setStep(categoryDto.getStep() + resultDto.getStep());
				categoryDto.setNone(categoryDto.getNone() + resultDto.getNon());
				categoryDto.setComment(categoryDto.getComment() + resultDto.getComment());

				totalStep += resultDto.getStep();
				totalNone += resultDto.getNon();
				totalComment += resultDto.getComment();
			}
			if (useNonCategory) {
				categories.add(nonCategory);
			}

			Collections.sort(categories, new Comparator<CategoryStepDto>() {
				public int compare(CategoryStepDto o1, CategoryStepDto o2) {
					if (o1.getCategory().length() == 0 && o2.getCategory().length() == 0) {
						return 0;
					}
					if (o1.getCategory().length() == 0) {
						return 1;
					}
					if (o2.getCategory().length() == 0) {
						return -1;
					}
					return o1.getCategory().compareTo(o2.getCategory());
				}
			});

			// カテゴリ・ファイルタイプが無指定の場合はnullから空文字に修正する。(fishplate対応)
			List<CountResult> resultList = new ArrayList<CountResult>();
			for (CountResult r : result) {
				if (r.getCategory() == null) {
					r.setCategory("");
				}
				if (r.getFileType() == null) {
					r.setFileType("未対応");
				}
				resultList.add(r);
			}
			OriginalCountResult[] originalResults = resultList.toArray(new OriginalCountResult[result.length]);

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("results", originalResults);
			data.put("categories", categories);
			data.put("totalStep", totalStep);
			data.put("totalNone", totalNone);
			data.put("totalComment", totalComment);

			return merge(in, data);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Fisshplateを使用してExcelファイルを生成します。 引数で与えたテンプレートの入力ストリームはこのメソッド内でクローズされます。
	 */
	private static byte[] merge(InputStream in, Map<String, Object> data) throws Exception {
		FPTemplate template = new FPTemplate();
		HSSFWorkbook wb;

		try {
			wb = template.process(in, data);
		} catch (Exception ex) {
			throw ex;
		} finally {
			Util.close(in);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		wb.write(out);

		return out.toByteArray();
	}

	private static CategoryStepDto getCategoryDto(List<CategoryStepDto> categoryResult, String category) {
		for (CategoryStepDto categoryDto : categoryResult) {
			if (categoryDto.getCategory().equals(category)) {
				return categoryDto;
			}
		}

		CategoryStepDto categoryDto = new CategoryStepDto();
		categoryDto.setCategory(category);
		categoryResult.add(categoryDto);

		return categoryDto;
	}
}
