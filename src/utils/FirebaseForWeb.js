const injectFirebaseAndBootUp = async callback => {
  // Load Firebase SDKs first
  await enqueueScripts([
    'https://www.gstatic.com/firebasejs/8.9.1/firebase-app.js',
    'https://www.gstatic.com/firebasejs/8.9.1/firebase-auth.js',
    'https://www.gstatic.com/firebasejs/8.9.1/firebase-firestore.js',
    'https://www.gstatic.com/firebasejs/8.9.1/firebase-functions.js',
    'https://www.gstatic.com/firebasejs/8.9.1/firebase-analytics.js',
    'https://www.gstatic.com/firebasejs/8.9.1/firebase-storage.js',
  ])

  // Initialize Firebase after scripts are loaded
  window.firebaseInitialized = () => {
    initializeApp() // Initialize Firebase
    callback() // Execute callback (start app)
  }

  // Initialize Firebase
  initializeApp()
}
