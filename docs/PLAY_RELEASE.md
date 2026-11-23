# Llave360 4.4.0 — publicación en Google Play

La rama `release/4.4.0` es la fuente de la versión a publicar.

- Application ID: `com.aldren.llave360`
- Version name: `4.4.0`
- Version code: `44`
- Archivo de salida esperado: `app/build/outputs/bundle/release/app-release.aab`

## Configuración local

1. Copiá `local.properties.example` como `local.properties` y cargá los valores reales de Supabase.
2. Creá la carpeta `keystore`.
3. Generá la llave de firma una sola vez y resguardala fuera de Git:

   ```bat
   keytool -genkeypair -v -keystore keystore\llave360-release.jks -storetype JKS -alias llave360 -keyalg RSA -keysize 2048 -validity 10000
   ```

4. Copiá `keystore.properties.example` como `keystore.properties` y escribí las cuatro credenciales que definiste.

`local.properties`, `keystore.properties` y el archivo `.jks` están ignorados por Git. No hay que subirlos ni enviarlos por chat.

## Verificación y paquete

Desde la raíz del proyecto en Windows:

```bat
gradlew.bat :app:testDebugUnitTest :app:assembleDebug
gradlew.bat :app:bundleRelease
```

La configuración bloquea un `bundleRelease` si falta la firma local, para evitar que se genere un AAB no publicable o firmado con una clave equivocada.

## Subida a Play Console

1. Verificá en Play Console que el paquete `com.aldren.llave360` esté disponible para esta ficha. Si ya existe una ficha publicada con otro package name, esa app no puede cambiarlo: hay que usar el package original o crear una ficha nueva.
2. Subí el AAB a **Pruebas internas** primero.
3. Instalalo desde el enlace de prueba, revisá carga de catálogo, imágenes, navegación y el flujo principal.
4. Completá la ficha de tienda, clasificación de contenido, seguridad de datos y política de privacidad.
5. Sólo después promovelo a producción.

Si Play marca que el version code 44 ya fue usado, incrementá únicamente `versionCode` antes de volver a generar el AAB.
