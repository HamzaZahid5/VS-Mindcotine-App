// metro.config.js
const { getDefaultConfig } = require('metro-config')

module.exports = (async () => {
  const {
    resolver: { assetExts, sourceExts },
  } = await getDefaultConfig()

  return {
    resolver: {
      sourceExts: [...sourceExts, 'js', 'json', 'ts', 'tsx'],
      assetExts: [...assetExts, 'mp3', 'png', 'jpg'],
    },
    transformer: {
      assetPlugins: ['expo-asset/tools/hashAssetFiles'],
    },
    server: {
      rewriteRequestUrl: url => {
        if (!url.endsWith('.bundle')) {
          return url
        }
        // only apply this for iOS
        if (url.includes('platform=ios')) {
          return url + '?dev=true&minify=false&modulesOnly=false&runModule=true'
        }
        return url
      },
    },
  }
})()
