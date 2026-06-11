# PracticaSupaBase — ListView + Glide + Supabase SDK (UTEQ)

Aplicación Android nativa en **Kotlin** para la asignatura **Aplicaciones Móviles** (6.º semestre, UTEQ). Consulta la tabla `alumnos` en **Supabase** mediante el SDK oficial y muestra los registros en un **ListView** con diseño personalizado, fotos circulares con **Glide** y filtros por semestre y materia.

Variante renombrada del proyecto docente **SDKSupaBase**, siguiendo el mismo enfoque de refactor que **BancoPZambrano**: identidad técnica distinta (paquete, recursos, actividad principal) con la misma funcionalidad y textos UTEQ.

---

## Identidad del proyecto (renombrado)

| Elemento | Valor |
|----------|-------|
| Carpeta / `rootProject.name` | `PracticaSupaBase` |
| `applicationId` / namespace | `com.estudiante.practicasupabase` |
| Actividad principal (launcher) | `PantallaAlumnosActivity` |
| Actividad secundaria | `ActividadListaTextoActivity` |
| Tema | `Theme.PracticaSupaBase` |
| Etiqueta launcher | Practica Supabase UTEQ |

---

## Requisitos de la práctica (PDF)

- **UI:** `ImageView` (logo UTEQ), `Spinner` (semestre y materia), `ListView` con ítem personalizado.
- **Ítem de lista:** foto, nombre completo, correo, teléfono e iconos descriptivos.
- **Datos:** SDK Supabase, consulta asíncrona a tabla `alumnos`, orden alfabético por `nombres`.
- **Imágenes:** Glide con `circleCrop()` desde URLs.
- **Modelo:** `data class Alumno(id, nombres, correo, telefono, foto)`.
- **Adaptador:** `AlumnoAdapter` hereda de `ArrayAdapter<Alumno>`.
- **Credenciales:** en `local.properties` → `BuildConfig` (no en código fuente).
- **Componentes permitidos:** ListView, ArrayAdapter, Supabase SDK, Glide, Spinner, ImageView, TextView, LinearLayout, RelativeLayout, ConstraintLayout.
- **Prohibidos:** RecyclerView, Compose, Retrofit, Volley, Firebase.

---

## Stack tecnológico

| Tecnología | Uso |
|------------|-----|
| Kotlin | Lenguaje principal |
| Supabase SDK BOM 3.6.0 | PostgREST (`postgrest-kt`) |
| Glide 4.16.0 | Fotos circulares de alumnos |
| Ktor Client 3.5.0 | Cliente HTTP Android |
| Kotlinx Serialization | Modelos `Alumno`, `Materia` |
| Coroutines + `lifecycleScope` | Consultas asíncronas |

---

## Estructura del proyecto

```
PracticaSupaBase/
├── app/src/main/java/com/estudiante/practicasupabase/
│   ├── PantallaAlumnosActivity.kt    # Launcher: spinners + ListView
│   ├── ActividadListaTextoActivity.kt
│   ├── AlumnoAdapter.kt
│   ├── Alumno.kt
│   ├── Materia.kt
│   ├── SupabaseManager.kt
│   └── SupabaseErrorHandler.kt
├── app/src/main/res/layout/
│   ├── activity_pantalla_alumnos.xml
│   ├── activity_lista_texto.xml
│   └── fila_estudiante.xml
├── local.properties.example
└── README.md
```

---

## Configuración de Supabase

1. Copiar `local.properties.example` como `local.properties` en la raíz del proyecto.
2. Completar `sdk.dir`, `SUPABASE_URL` y `SUPABASE_KEY`.
3. **No** subir `local.properties` a GitHub (está en `.gitignore`).

Gradle expone las credenciales vía `BuildConfig.SUPABASE_URL` y `BuildConfig.SUPABASE_KEY` en `SupabaseManager.kt`.

Tablas esperadas: `alumnos` (`id`, `nombres`, `correo`, `telefono`, `foto`) y `materias` (`id`, `nombre`, `nivel`). Fotos desde `https://sga.uteq.edu.ec` + campo `foto`.

---

## Cómo ejecutar

1. Abrir **PracticaSupaBase** en Android Studio.
2. Sincronizar Gradle.
3. Configurar `local.properties` con credenciales válidas.
4. Ejecutar en emulador (API 26+) o dispositivo físico.

### Compilar desde terminal

```bash
gradlew assembleDebug
```

---

## Recursos renombrados (vs SDKSupaBase)

| Original | PracticaSupaBase |
|----------|------------------|
| `logo.jpeg` | `logo_institucional_uteq.jpeg` |
| `mail.png` | `icono_correo.png` |
| `call.png` | `icono_telefono.png` |
| `ic_action_name` | `icono_perfil_placeholder` |
| `activity_main2.xml` | `activity_pantalla_alumnos.xml` |
| `item_alumno.xml` | `fila_estudiante.xml` |

---

## Entrega

- Repositorio GitHub con código, README, capturas e instrucciones.
- PDF con código de `Alumno`, `AlumnoAdapter`, consulta Supabase, capturas y URL del repo.

---

## Notas

- El proyecto fuente **SDKSupaBase** no se modifica.
- Proyecto académico — Universidad Técnica Estatal de Quevedo (UTEQ) · 2026.
