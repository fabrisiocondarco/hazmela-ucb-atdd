import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/****************************************/
// Historia de Usuario:
// Como estudiante quiero registrarme en Hazmela UCB
// para poder acceder a la plataforma.
//
// Prueba de Aceptación:
// Verificar el registro exitoso de un usuario con datos válidos.
//
// PASOS:
// 1. Ingresar a la página de registro.
// 2. Completar nombre.
// 3. Completar correo institucional válido.
// 4. Completar carrera.
// 5. Completar contraseña válida.
// 6. Presionar el botón Registrarse.
//
// Resultado Esperado:
// El sistema debe redireccionar al usuario al Feed.
/****************************************/

public class RegistroExitosoTest extends BaseTest {

    @Test
    public void verificarRegistroExitoso() throws InterruptedException {

        /********** PREPARACION DE LA PRUEBA **********/

        driver.get("http://localhost:8090/register");

        String correoUnico =
                "fabricio"
                + System.currentTimeMillis()
                + "@ucb.edu.bo";

        Thread.sleep(2000);

        /********** LOGICA DE LA PRUEBA **********/

        // PASO 1: Ingresar nombre completo
        driver.findElement(By.id("name"))
                .sendKeys("Fabricio Condarco");

        // PASO 2: Ingresar correo institucional
        driver.findElement(By.id("email"))
                .sendKeys(correoUnico);

        // PASO 3: Ingresar carrera
        driver.findElement(By.id("career"))
                .sendKeys("Ingenieria Informatica");

        // PASO 4: Ingresar contraseña
        driver.findElement(By.id("password"))
                .sendKeys("Password123");

        // PASO 5: Confirmar contraseña
        driver.findElement(By.id("confirmPassword"))
                .sendKeys("Password123");

        // PASO 6: Presionar botón Registrarse
        driver.findElement(
                By.xpath("//button[@type='submit']")
        ).click();

        Thread.sleep(3000);

        /********** VERIFICACION DEL RESULTADO ESPERADO **********/

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/feed")
        );

        System.out.println(
                "Registro exitoso. URL actual: "
                        + driver.getCurrentUrl()
        );
    }
}