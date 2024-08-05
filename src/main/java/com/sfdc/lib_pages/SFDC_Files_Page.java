package com.sfdc.lib_pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date: 24/Sept/2020
 * 
 * 
 *         Files page for SFDC Application
 */

public class SFDC_Files_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Files@: ";

	public SFDC_Files_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Files
	 */
	public void selectFiles() throws IOException {
		try {

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Files
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.files.FilesMenu);
			reportStatusPass(methodName + " Selected Files from menu", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not open Home tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Files Page is opened from navigation menu
	 * 
	 */
	public void verifyFilesPageOpened() throws IOException {
		try {

			// Verify Files Page is opened by verifying filesheader object and OwnedByMe
			// section
			if ((sf.seleU.isElementPresent(sf.files.filesSpanHeader))
					&& (sf.seleU.isElementPresent(sf.files.filesOwnedByMeSection))) {
				reportStatusPass(methodName
						+ " Files Page opened successfully. Verified Owned By me section Present and files span header present at top",
						true, true);
			} else {
				reportStatusFail(methodName + " Files Page is not opened successfully", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verification Failure for Files page open status", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Extract number of rows in Files table
	 * 
	 */
	public void getNumberofRowsInFilesListSection() throws IOException {

		try {
			// Store number of files in Files Table
			sf.seleU.hardwait(4000);
			InputData.filesTableNoOfRows = sf.files.filesTableRows.size();
			reportStatusPass(methodName + " No. of files in table are " + InputData.filesTableNoOfRows, false, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot extract number of rows in files table in Files page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Files table number of rows/files in Files Page
	 * 
	 */
	public void verifyFilesTableSize() throws IOException {

		try {

			// Verify number of files in files table
			sf.seleU.hardwait(4000);
			if (sf.files.filesTableRows.size() == (InputData.filesTableNoOfRows + 1)) {
				InputData.noOfFiles = InputData.noOfFiles + 1;
				reportStatusPass(methodName + " Files List contains the newly added file/s", true, true);
			} else {
				reportStatusFail(methodName + " Files List does not contains the newly added file/s", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Verification Failure for comparing number of rows in Files Table in Files page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify upload button not present under libraries in Files
	 *                     Page
	 * 
	 */
	public void verifyUploadButtonLibraries() throws IOException {

		try {

			// Verifying Upload button presence
			sf.seleU.hardwait(4000);
			if (!sf.seleU.isElementPresent(sf.files.filesUploadButton)) {
				reportStatusPass(methodName + " Upload Button is not present under libraries section as expected", true,
						true);
			} else {
				reportStatusFail(methodName + " Upload Button is present under libraries section which is not expected",
						true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Verification Failure for comparing number of rows in Files Table in Files page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Choose section from Files Page given as argument
	 * 
	 * 
	 */
	public void chooseSection(String section) throws IOException {

		try {

			// Select section in Files Page
			sf.seleU.hardwait(4000);
			boolean success = true;
			switch (section) {
			case "Owned By Me":
				sf.seleU.clickElementByJSE(sf.files.filesOwnedByMeSection);
				break;
			case "Shared With Me":
				sf.seleU.clickElementByJSE(sf.files.filesSharedWithMeSection);
				break;
			case "Recent":
				sf.seleU.clickElementByJSE(sf.files.filesRecentSection);
				break;
			case "Following":
				sf.seleU.clickElementByJSE(sf.files.filesFollowingSection);
				break;
			case "Libraries":
				sf.seleU.clickElementByJSE(sf.files.filesLibrariesSection);
				break;
			default:
				reportStatusFail(methodName + " Could not select " + section + " in Files Page", true);
				success = false;

			}
			if (success)
				reportStatusPass(methodName + " Selected " + section + " in Files Page", true, false);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in choosing " + section + " section", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Uploads Files from particular section
	 * 
	 */
	public void uploadFiles() throws IOException {

		try {

			// Click on Upload Files
			sf.seleU.clickOnElement(sf.files.filesUploadButton);
			reportStatusPass(methodName + " Uploading new file", false, false);
			System.out.println("2");
			sf.seleU.hardwait(6000);

			// Send path of files to be uploaded
			/*
			 * sf.seleU.setClipboardData(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE);
			 * sf.seleU.paste_Function();
			 */
			Runtime.getRuntime().exec(
					Constants.RESOURCES_DIR + "\\fileSelector.exe" + " " + Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE);
			sf.seleU.hardwait(16000);

			reportStatusPass(
					methodName + " Trying to upload "
							+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE),
					false, false);

			sf.seleU.waitElementToBeClickable(sf.files.filesUploadDoneButton);
			System.out.println(sf.seleU.getTextFromWebElement(sf.files.filesUploadedtext));
			// Verify File was uploaded successfully and Click done once uploaded
			if (sf.seleU.getTextFromWebElement(sf.files.filesUploadedtext).equalsIgnoreCase("1 of 1 file uploaded")) {
				reportStatusPass(methodName + " File "
						+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE)
						+ " uploaded successfully", true, true);
			} else {
				reportStatusFail(methodName + " Could not upload file "
						+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE), true);
			}
			sf.seleU.waitElementToBeClickable(sf.files.filesUploadDoneButton);
			sf.seleU.clickElementByJSE(sf.files.filesUploadDoneButton);
			reportStatusPass(methodName + " Clicked on Done", false, false);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in uploading file", e);
			e.printStackTrace();
		}

	}

/**
* @throws IOException
* 
*  Uploads Files from particular section through Robot Class
* 
*/	
	public static void setClipBoard(String file) 
	  {
		try
		{
	     StringSelection obj= new StringSelection(file);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(obj, null);
	    }
		catch(Throwable e) 
		{
		  e.printStackTrace();
		}
	  }
	  
	  public static void uploadFileRobotClass(String filePath) throws AWTException 
	  {
		  try
		  {
		  setClipBoard(filePath);
		  Robot rb= new Robot();
		  rb.keyPress(KeyEvent.VK_CONTROL);
		  rb.keyPress(KeyEvent.VK_V);
		  rb.keyRelease(KeyEvent.VK_CONTROL);
		  rb.keyRelease(KeyEvent.VK_V);
		  rb.setAutoDelay(2000);
		  rb.keyPress(KeyEvent.VK_ENTER);
		  rb.keyRelease(KeyEvent.VK_ENTER);
	      }
		  catch(Throwable e) 
		  {
			e.printStackTrace();
		  }
	  } 

	/**
	 * @throws IOException
	 * 
	 *                     Uploads Files from particular section
	 * 
	 */
	public void uploadFilesWithOutVerify() throws IOException {

		try {

			// Click on Upload Files
//			sf.seleU.clickOnElement(sf.files.filesUploadButton);
//			reportStatusPass(methodName + " Uploading new file", false, false);
//			System.out.println("2");
			sf.seleU.hardwait(6000);

			// Send path of files to be uploaded
			/*
			 * sf.seleU.setClipboardData(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE);
			 * sf.seleU.paste_Function();
			 */
			Runtime.getRuntime().exec(
					Constants.RESOURCES_DIR + "\\fileSelector.exe" + " " + Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE);
			sf.seleU.hardwait(16000);

			reportStatusPass(
					methodName + " Trying to upload "
							+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE),
					false, false);

			sf.seleU.wait(4000);
			sf.seleU.waitElementToBeClickable(sf.files.filesUploadDoneButton);
			sf.seleU.wait(8000);
			// Verify File was uploaded successfully and Click done once uploaded
			if (sf.seleU.getTextFromWebElement(sf.files.filesUploadedtext).equalsIgnoreCase("1 of 1 file uploaded")) {
				reportStatusPass(methodName + " File "
						+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE)
						+ " uploaded successfully", true, true);
			} else {
				reportStatusFail(methodName + " Could not upload file "
						+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE), true);
			}
			sf.seleU.waitElementToBeClickable(sf.files.filesUploadDoneButton);
			sf.seleU.clickElementByJSE(sf.files.filesUploadDoneButton);
			reportStatusPass(methodName + " Clicked on Done", false, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in uploading file", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Create New Library in Files Page
	 * 
	 */
	public void createNewLibrary() throws IOException {

		try {

			// Returns Random name for library
			String libName = InputData.libraryName;

			// Click on New Library
			sf.seleU.clickOnElement(sf.files.newLibraryButton);
			reportStatusPass(methodName + " Creating new library", false, false);

			// Fill LibraryName and save
			sf.seleU.enterText(sf.files.libraryNameTextBox, libName);
			sf.seleU.hardwait(2000);
			sf.seleU.waitElementToBeClickable(sf.files.librarySaveButton);
			sf.seleU.clickOnElement(sf.files.librarySaveButton);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Filled library name " + libName + " and saved", false, false);
			// Verify Library created or not
			if (sf.seleU.getTextFromWebElement(sf.files.firstLibraryOrFolderName).equalsIgnoreCase(libName)) {
				reportStatusPass(methodName + " Library " + libName + " created successfully", true, true);
			} else {
				reportStatusFail(methodName + " Could not create library " + libName, true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating new library in Files Page", e);
			e.printStackTrace();
		}
	}

	
	/**
	 * @throws IOException
	 * 
	 *                     Rename old Library in Files Page
	 * 
	 */
	public void renameOldLibrary() throws IOException {

		try {

			// Returns Random name for library
			JavascriptExecutor js = (JavascriptExecutor)driver;
			InputData.libraryName = "lib" + AdditionalUtilities.generateRandomCharacters(3);
			String newLibName = InputData.libraryName;
			
			// Click on Library and Library Dropdown 
			sf.seleU.clickOnElement(sf.files.filesLibrariesSection);
			js.executeScript("arguments[0].click();", sf.files.libraryDropdown);
			sf.seleU.hardwait(2000);
			sf.seleU.clickOnElement(sf.files.editLibraryDetails);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Renaming old library", true, true);
			
			// Fill New Library Name and save
			sf.seleU.clearAndEnterText(sf.files.libraryNameTextBox, newLibName);
			sf.seleU.hardwait(2000);
			sf.seleU.waitElementToBeClickable(sf.files.librarySaveButton);
			sf.seleU.clickOnElement(sf.files.librarySaveButton);
			sf.seleU.hardwait(5000);
			sf.seleU.clickOnElement(sf.files.filesLibrariesSection);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Filled library name " + newLibName + " and saved", true, true);
			
			// Verify Library renamed or not 
			if (sf.seleU.getTextFromWebElement(sf.files.firstLibraryOrFolderName).equalsIgnoreCase(newLibName)) {
				reportStatusPass(methodName + " Library " + newLibName + " renamed successfully", true, true);
			} 
			else {
				reportStatusFail(methodName + " Could not rename library " + newLibName, true);
			}
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in renaming old library in Files Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Create New Folder in library
	 * 
	 */
	public void createNewFolderInLibrary() throws IOException {

		try {

			sf.seleU.hardwait(2000);

			// Click on library and create new folder
			sf.seleU.clickOnElement(sf.files.firstLibraryOrFolderFromList);
			sf.seleU.hardwait(2000);
			sf.seleU.clickOnElement(sf.files.newFolderButton);
			reportStatusPass(methodName + " Creating new folder inside library", false, false);

			sf.seleU.enterText(sf.files.folderNameTextBox, InputData.folderName);
			sf.seleU.waitElementToBeClickable(sf.files.folderSaveButton);
			sf.seleU.clickOnElement(sf.files.folderSaveButton);
			reportStatusPass(methodName + " Filled folder name " + InputData.folderName + " and clicked on Save", false,
					false);

			// Verify folder created successfully
			if (sf.seleU.getTextFromWebElement(sf.files.firstLibraryOrFolderName)
					.equalsIgnoreCase(InputData.folderName)) {
				reportStatusPass(methodName + " Folder " + InputData.folderName + " created successfully in library "
						+ InputData.libraryName, true, true);
			} else {
				reportStatusFail(methodName + " Could not create folder " + InputData.folderName + " in library "
						+ InputData.libraryName, true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating folder " + InputData.folderName + " in library "
					+ InputData.libraryName, e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Add Files in folder in library under Files Page
	 * 
	 */
	public void addFilesInFolder() throws IOException {

		try {

			sf.seleU.hardwait(2000);

			// Click on folder and add new file
			sf.seleU.clickOnElement(sf.files.firstLibraryOrFolderFromList);
			reportStatusPass(methodName + " Opened folder " + InputData.folderName + " to add file", true, false);

			sf.seleU.hardwait(2000);
			getNumberofRowsInFilesListSection();
			sf.seleU.clickOnElement(sf.files.addFilesButton);
			reportStatusPass(methodName + " Adding file "
					+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE) + " in folder "
					+ InputData.folderName, true, false);
			uploadFiles();

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Adding file "
					+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE) + " in folder "
					+ InputData.folderName, e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify upload file success message
	 * 
	 */
	public void verifyUploadSuccessMessage() throws IOException {
		try {

			// Verify success message displayed or not
			if (sf.seleU.isElementPresent(sf.files.uploadSuccessMessage)) {
				InputData.noOfFiles = InputData.noOfFiles + 1;
				reportStatusPass(methodName + " File uploaded successfully.", true, true);
			} else {
				reportStatusFail(methodName + " Files not uploaded successfully", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not verify file upload success message", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Delete new files created
	 * 
	 */
	public void deleteFiles() throws IOException {

		try {

			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Deleting " + InputData.noOfFiles + " recently uploaded files ", false,
					false);

			// Click on file one by one and delete
			for (int i = 0; i < InputData.noOfFiles; i++) {

				// extract number of files in table
				getNumberofRowsInFilesListSection();

				sf.seleU.clickOnElement(sf.files.filesNames.get(0));
				sf.seleU.hardwait(4000);
				reportStatusPass(methodName + " Opened file for deletion", true, false);

				sf.seleU.clickOnElement(sf.files.showMoreIcon);
				sf.seleU.hardwait(2000);
				sf.seleU.clickOnElement(sf.files.deleteFileMenuItem);
				sf.seleU.clickOnElement(sf.files.deleteFileButton);
				sf.seleU.hardwait(2000);

				// Verify file was deleted
				if (sf.files.filesTableRows.size() == (InputData.filesTableNoOfRows - 1)) {
					reportStatusPass(methodName + " File was deleted successfully", true, false);
				} else {
					reportStatusPass(methodName + " Could not delete file", true, false);
				}
			}
			InputData.noOfFiles = 0;
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in deleting files", e);
			e.printStackTrace();
		}
	}

}
