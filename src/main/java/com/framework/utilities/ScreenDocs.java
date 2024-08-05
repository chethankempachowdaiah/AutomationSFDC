package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import com.framework.base.Base;
import com.framework.base.Constants;

public class ScreenDocs extends Base {

	public static void createScreenDoc(String screenDocName) throws FileNotFoundException {

		if (screenDocName.equals(classname)) {
			docxGeneric = new XWPFDocument();
			CTSectPr sectPr = docxGeneric.getDocument().getBody().addNewSectPr();
			CTPageMar pageMar = sectPr.addNewPgMar();
			pageMar.setLeft(BigInteger.valueOf(650L)); // 650
			pageMar.setTop(BigInteger.valueOf(720L)); // 720
			pageMar.setRight(BigInteger.valueOf(650L)); // 650
			pageMar.setBottom(BigInteger.valueOf(720L)); // 720
			XWPFParagraph paragraph = docxGeneric.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			runGeneric = paragraph.createRun();
			outGeneric = new FileOutputStream(userDirPath() + screenDocName + "_"
					+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".docx");

			runGeneric.addBreak();
			runGeneric.addCarriageReturn();
			runGeneric.addBreak(BreakType.TEXT_WRAPPING);
		} else {
			docxDataSpecific = new XWPFDocument();
			CTSectPr sectPr = docxDataSpecific.getDocument().getBody().addNewSectPr();
			CTPageMar pageMar = sectPr.addNewPgMar();
			pageMar.setLeft(BigInteger.valueOf(650L)); // 650
			pageMar.setTop(BigInteger.valueOf(720L)); // 720
			pageMar.setRight(BigInteger.valueOf(650L)); // 650
			pageMar.setBottom(BigInteger.valueOf(720L)); // 720
			XWPFParagraph paragraph = docxDataSpecific.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			runDataSpecific = paragraph.createRun();
			outDataSpecific = new FileOutputStream(userDirPath() + screenDocName + "_"
					+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".docx");

			runDataSpecific.addBreak();
			runDataSpecific.addCarriageReturn();
			runDataSpecific.addBreak(BreakType.TEXT_WRAPPING);
		}

	}

	public static void captureScreenShot(XWPFDocument docx, XWPFRun run, FileOutputStream out, String message)
			throws IOException, InvalidFormatException {

		String screenshot_name = "test.png";
		File sourceSnap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationSnap = Constants.REPORTS_DIR + screenshot_name;
		File snapFile = new File(destinationSnap);
		FileUtils.copyFile(sourceSnap, snapFile);
		try (InputStream pic = new FileInputStream(snapFile)) {
			run.addBreak();
			run.setText(DateTimeUtilities.currentSystemDate("yyyy/MM/dd HH:mm:ss") + "   ::  " + message);
			run.addCarriageReturn();
			run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(550), Units.toEMU(430));
			run.addBreak(BreakType.TEXT_WRAPPING);
			pic.close();
			snapFile.delete();
		}
	}

	public static void saveScreenDoc(String screenDocName) {
		try {

			if (screenDocName.equals(classname)) {
				docxGeneric.write(outGeneric);
				outGeneric.flush();
				outGeneric.close();
				docxGeneric.close();
			} else {
				docxDataSpecific.write(outDataSpecific);
				outDataSpecific.flush();
				outDataSpecific.close();
				docxDataSpecific.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}