// import 'react-native-gesture-handler'
// import { registerRootComponent } from 'expo'
// import injectFirebaseAndBootUp from './src/utils/FirebaseForWeb'
// import App from './App'

// // Modify to ensure `registerRootComponent` is executed after Firebase is initialized
// export default () => {
//   injectFirebaseAndBootUp(() => {
//     // Call this only after Firebase is initialized
//     registerRootComponent(App)
//   })
// }

import 'react-native-gesture-handler'
import { registerRootComponent } from 'expo'
import injectFirebaseAndBootUp from './src/utils/FirebaseForWeb'
import App from './App'

// Call registerRootComponent after Firebase has been initialized
export default () => {
  injectFirebaseAndBootUp(() => {
    // Only call registerRootComponent after Firebase is initialized
    registerRootComponent(App)
  })
}
