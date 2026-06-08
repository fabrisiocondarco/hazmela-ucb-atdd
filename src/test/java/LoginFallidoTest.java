import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/****************************************/
// Historia de Usuario:
//
// Como estudiante registrado quiero
// iniciar sesión en Hazmela UCB.
//
// Prueba de Aceptación:
//
// Verificar que el sistema rechace
// una contraseña incorrecta.
//
// CASO BASE: Login con contraseña incorrecta #39
//
// PASOS:
//
// 1. Ingresar a la página Login
// 2. Escribir correo registrado
// 3. Escribir contraseña incorrecta
// 4. Presionar Iniciar Sesión
//
// Resultado Esperado:
//
// Debe mostrarse:
//
// "Correo o contraseña incorrectos."
//
// El sistema no debe permitir el acceso
// al feed y debe permanecer en la página
// de login mostrando el error.
/****************************************/

public class LoginFallidoTest extends BaseTest {

    @Test
    public void verificarPasswordIncorrecta() {

        /********** Preparación de la prueba **********/
        // Se navega a la página de login del sitio web
        // Se espera 3 segundos para que cargue completamente el formulario

        driver.get("http://localhost:8090/login");

        try {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*********** Lógica de la prueba ***********/
        // PASO 1: Se localiza el campo de correo por su ID "email"
        // PASO 2: Se ingresa un correo institucional que SÍ está registrado en el sistema

        WebElement correo =
                driver.findElement(By.id("email"));

        correo.sendKeys("fabrisio.condarco@ucb.edu.bo");

        // PASO 3: Se localiza el campo de contraseña por su ID "password"
        // PASO 4: Se ingresa una contraseña INCORRECTA (no coincide con la registrada)

        WebElement password =
                driver.findElement(By.id("password"));

        password.sendKeys("PasswordIncorrecto123");

        // PASO 5: Se localiza el botón "Iniciar Sesión" mediante XPath
        // PASO 6: Se hace clic en el botón para enviar el formulario

        WebElement botonLogin =
                driver.findElement(
                        By.xpath("//button[contains(text(),'Iniciar Sesión')]")
                );

        botonLogin.click();

        // Se espera 3 segundos para que el sistema procese la solicitud
        // y muestre el mensaje de error

        try {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        /************ Verificación (Assert) ************/
        // PASO 7: Se localiza el mensaje de error esperado mediante XPath
        // El mensaje debe contener el texto "Correo o contraseña incorrectos."

        WebElement mensajeError =
                driver.findElement(
                        By.xpath("//*[contains(.,'Correo o contraseña incorrectos.')]")
                );

        // PASO 8: Se verifica que el mensaje de error sea visible en la pantalla
        // Si el mensaje no aparece, la prueba falla

        Assert.assertEquals(
                true,
                mensajeError.isDisplayed()
        );

        // Se imprime en consola la confirmación de que el mensaje se mostró

        System.out.println(
                "Se muestra el mensaje de error: "
                        + mensajeError.isDisplayed()
        );
    }
}