package ayrat.salavatovich.gmail.com.day_63;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class SendMessage extends UiAutomatorTestCase {

	final static String TAG = "ayrat.salavatovich.gmail.com.day_63.SendMessage";

	public void test() throws UiObjectNotFoundException {
		// Default parameters
		String toNumber = "0123456789";
		String text = "Test message";
		Log.i(TAG, "Start SendMessage");
		findAndRunApplication();
		sendMessage(toNumber, text);
		exitToMainWindow();
		Log.i(TAG, "End SendMessage");
	}

	private void findAndRunApplication() throws UiObjectNotFoundException {
		// Go to main screen
		getUiDevice().pressHome();
		// Find App menu button
		UiObject allAppsButton = new UiObject(
				new UiSelector().description("Apps"));
		// Click on app menu button and wait new window
		allAppsButton.clickAndWaitForNewWindow();
		// Find App tab
		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));
		// Click on app tab
		appsTab.click();
		// Find scroll object (menu scroll)
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		// Set the swiping mode to horizontal (the default is vertical)
		appViews.setAsHorizontalList();
		// Find Messaging application
		UiObject settingsApp = appViews.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				"Messaging");
		// Open Messaging application
		settingsApp.clickAndWaitForNewWindow();

		// Validate that the package name is the expected one
		UiObject settingsValidation = new UiObject(
				new UiSelector().packageName("com.android.mms"));
		assertTrue("Unable to detect Messaging", settingsValidation.exists());
	}

	private void sendMessage(String toNumber, String text)
			throws UiObjectNotFoundException {
		// Find and click New message button
		UiObject newMessageButton = new UiObject(new UiSelector().className(
				"android.widget.TextView").description("New message"));
		newMessageButton.clickAndWaitForNewWindow();

		// Find to box and enter the number into it
		UiObject toBox = new UiObject(new UiSelector().className(
				"android.widget.MultiAutoCompleteTextView").instance(0));
		toBox.setText(toNumber);
		// Find text box and enter the message into it
		UiObject textBox = new UiObject(new UiSelector().className(
				"android.widget.EditText").instance(0));
		textBox.setText(text);

		// Find send button and send message
		UiObject sendButton = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").description("Send"));
		sendButton.click();
	}

	private void exitToMainWindow() {
		// Find New message button
		UiObject newMessageButton = new UiObject(new UiSelector().className(
				"android.widget.TextView").description("New message"));

		// Press back button while new message button doesn't exist
		while (!newMessageButton.exists()) {
			getUiDevice().pressBack();
		}
	}
}
