describe('Example', () => {
  beforeAll(async () => {
    // Launch the app before starting the tests
    await device.launchApp();
  });

  it('should display CORE button and press it', async () => {
    // Wait for the CORE_BUTTON to be visible within a reasonable timeout

    await new Promise(resolve => setTimeout(resolve, 2000)); // Wait for 2 seconds
    await new Promise(resolve => setTimeout(resolve, 2000)); // Wait for 2 seconds

    // await waitFor(element(by.id('CORE_BUTTON')))
    //   .toBeVisible()
    //   .withTimeout(2000); // Wait for up to 14 seconds for the button to be visible

    // Check if the button is visible
    await expect(element(by.id('CORE_BUTTON'))).toBeVisible();

    // Tap on the CORE_BUTTON
   await element(by.id('CORE_BUTTON')).tap();

   await new Promise(resolve => setTimeout(resolve, 2000)); // Wait for 2 seconds

   await new Promise(resolve => setTimeout(resolve, 2000)); // Wait for 2 seconds
   await new Promise(resolve => setTimeout(resolve, 12000)); // Wait for 2 seconds

  });
});
