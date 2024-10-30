import { useState, useEffect } from 'react';
import { StyleSheet, View, Text, Button } from 'react-native';
import { multiply, extensionVersion } from 'additionalpackage';

export default function App() {
  const [result, setResult] = useState<number | undefined>();
  const [version, setVersion] = useState<string | null>(null);

  const fetchExtensionVersion = async () => {
    try {
      const ver = await extensionVersion();
      setVersion(ver);
    } catch (error) {
      console.error("Failed to fetch extension version:", error);
    }
  };
  useEffect(() => {
    multiply(6, 7).then(setResult);
    
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Button title="Get Extension Version" onPress={fetchExtensionVersion} />

     {version && <Text>Extension Version: {version}</Text>}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
