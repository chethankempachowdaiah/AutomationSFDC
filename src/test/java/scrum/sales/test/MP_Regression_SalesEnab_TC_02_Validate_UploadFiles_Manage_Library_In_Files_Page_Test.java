package scrum.sales.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date : 24/Sep/2020
 * 
 *         Test signin as AE and opens File tab
 * 
 *         Verifies upload files button working fine under all sections
 *
 *         All sections - "Owned By Me","Shared With Me","Recent","Following"
 *
 *         Verifies upload file button is not present under Libraries section
 *
 *
 *         Created new library and a folder inside it
 *
 *         Add file to new folder and verify uploaded successfully or not
 *         
 *         22/06/2021 - Validate user with Permission set : “Manage Library” can Edit Library and Content.
 *      1. Login should be successfull.
		2. "Files" section should be displayed..
		3. "Libraries" page should be displayed.
		4. "New Library" details form should be displayed.
		5. Details are entered and Library is saved.
		6. Dropdown should have Rename option and should be selected.
		7. User should be successfully able to edit name and content
 */
public class MP_Regression_SalesEnab_TC_02_Validate_UploadFiles_Manage_Library_In_Files_Page_Test extends Base {

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 */
	@Test()
	public void test_verifyUploadFiles() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();
		sfdc.files.selectFiles();
		sfdc.files.verifyFilesPageOpened();

		// upload files from Owned By me section
		sfdc.files.chooseSection(sf.dataInput.ownedByMeFilesSection);
		// sfdc.files.getNumberofRowsInFilesListSection();
		sfdc.files.uploadFiles();
		// sfdc.files.verifyFilesTableSize();
		sfdc.files.verifyUploadSuccessMessage();

		/*
		 * // upload files from Shared With me section
		 * sfdc.files.chooseSection(sf.dataInput.sharedWithMeFilesSection);
		 * sfdc.files.uploadFiles(); sfdc.files.verifyUploadSuccessMessage();
		 * 
		 * // upload files from Recent section
		 * sfdc.files.chooseSection(sf.dataInput.recentFilesSection);
		 * sfdc.files.uploadFiles(); sfdc.files.verifyUploadSuccessMessage();
		 * 
		 * // upload files from Following section
		 * sfdc.files.chooseSection(sf.dataInput.followingFilesSection);
		 * sfdc.files.uploadFiles(); sfdc.files.verifyUploadSuccessMessage();
		 * 
		 */// delete newly created files
		sfdc.files.deleteFiles();

		// Verify upload files is not present under Libraries section
		sfdc.files.chooseSection(sf.dataInput.librariesSection);
		sfdc.files.verifyUploadButtonLibraries(); 
		// Create new library
		sfdc.files.createNewLibrary();

		// rename old library
		sfdc.files.renameOldLibrary();
				
		// Create new folder in library
		sfdc.files.createNewFolderInLibrary();

		// upload files in new folder
		sfdc.files.addFilesInFolder();

		// verify file uploaded
		sfdc.files.verifyFilesTableSize();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

	}

}
