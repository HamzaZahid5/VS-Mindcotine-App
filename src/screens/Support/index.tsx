import React, { useEffect, useState } from 'react'
import { homeBGColors } from '../../utils/config'
import { View, StyleSheet, useWindowDimensions, StatusBar } from 'react-native'
import Intercom, { Space, IntercomEvents } from '@intercom/intercom-react-native'
import { DefaultScreenPropType } from '../../../types'
import { BackgroundArt, TabbedScreen } from '@mindcoxr/rob'
import { useSelector } from 'react-redux'
import { USER_SUPPORT_PROFILE } from '../../store/selectors'

const Support = ({ navigation }: DefaultScreenPropType<'Main'>) => {
  const windowsDimension = useWindowDimensions()
  const profile = useSelector(USER_SUPPORT_PROFILE)
  const [isUserLogged, setIsUserLogged] = useState(false)

  useEffect(() => {
    if (!isUserLogged) {
      Intercom.presentSpace(Space.home)
      Intercom.loginUserWithUserAttributes({
        userId: profile.uid ? profile.uid : '',
        name: profile.display_name ? profile.display_name : '',
        customAttributes: {
          group: profile.group ? profile.group : '',
          uid: profile.uid ? profile.uid : '',
        },
      })
        .then(value => {
          setIsUserLogged(true)
        })
        .catch(err => console.log({ err }))
    }

    Intercom.addEventListener(IntercomEvents.IntercomWindowDidHide, () => {
      navigation.navigate('Home')
    })
  }, [isUserLogged, navigation, profile])

  return (
    <TabbedScreen colors={homeBGColors}>
      <StatusBar animated={true} />
      <BackgroundArt
        paddingTop={windowsDimension.height > windowsDimension.width ? 5 : 0}
        paddingBottom={0}
        colors={homeBGColors}
        source={require('../../../assets/images/bg_01.png')}
      />
      <View style={styles.container}></View>
    </TabbedScreen>
  )
}

export default Support

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
})
