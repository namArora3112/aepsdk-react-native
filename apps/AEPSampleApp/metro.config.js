const { getDefaultConfig, mergeConfig } = require('@react-native/metro-config');
const { resolve, join } = require('path');

const config = {
    transformer: {
        getTransformOptions: async () => ({
            transform: {
                experimentalImportSupport: false,
                inlineRequires: true,
            },
        }),
    },
    watchFolders: [
        resolve(__dirname, '../../packages'), 
        resolve(__dirname, '../../packages/awesome-library') // Add your package here
    ],
    resolver: {
        extraNodeModules: new Proxy(
            {},
            {
                get: (target, name) => {
                    if (typeof name !== 'string') {
                        return target[name];
                    }
                    if (
                        name &&
                        name.startsWith &&
                        name.startsWith('@adobe/react-native-aep')
                    ) {
                        const packageName = name.replace('@adobe/react-native-aep', '');
                        console.log('------packageName -> ' + packageName);
                        return join(__dirname, `../../packages/${packageName}`);
                    }
                    if (name === 'react-native-awesome-library') {
                        return resolve(__dirname, '../../packages/awesome-library'); // Add your package here
                    }
                    return join(__dirname, `node_modules/${name}`);
                },
            },
        ),
    },
};

module.exports = mergeConfig(getDefaultConfig(__dirname), config);
