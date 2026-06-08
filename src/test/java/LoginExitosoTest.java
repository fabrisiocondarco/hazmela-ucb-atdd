import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/****************************************/
// Historia de Usuario:
//
// Como estudiante registrado quiero
// iniciar sesión en Hazmela UCB
// utilizando mis credenciales válidas.
//
// Prueba de Aceptación:
//
// Verificar que un usuario registrado
// pueda ingresar correctamente al sistema.
//
// CASO BASE: Login exitoso #38
//
// PASOS:
//
// 1. Ingresar a la página Login
// 2. Escribir correo institucional válido
// 3. Escribir contraseña correcta
// 4. Presionar Iniciar Sesión
//
// Resultado Esperado:
//
// El sistema redirige al Feed principal
// (http://localhost:8090/feed) confirmando
// que el inicio de sesión fue exitoso.
/****************************************/

public class LoginExitosoTest extends BaseTest {

    @Test
    public void verificarLoginExitoso() {

        /********** Preparación de la prueba **********/
        // Se navega a la página de login del sitio web
        // Se espera 3 segundos para que cargue completamente el formulario

        driver.get("http://localhost:8090/login");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*********** Lógica de la prueba ***********/
        // PASO 1: Se localiza el campo de correo por su ID "email"
        // PASO 2: Se ingresa un correo institucional que SÍ está registrado en el sistema

        WebElement correo =
                driver.findElement(By.id("email"));

        correo.sendKeys("fabrisio.condarco@ucb.edu.bo");

        // PASO 3: Se localiza el campo de contraseña por su ID "password"
        // PASO 4: Se ingresa la contraseña CORRECTA asociada al correo registrado

        WebElement password =
                driver.findElement(By.id("password"));

        password.sendKeys("Prueba123@");

        // PASO 5: Se localiza el botón "Iniciar Sesión" mediante XPath
        // PASO 6: Se hace clic en el botón para enviar el formulario

        WebElement botonLogin =
                driver.findElement(
                        By.xpath("//button[contains(text(),'Iniciar Sesión')]")
                );

        botonLogin.click();

        // Se espera 5 segundos para que el sistema procese el login
        // y realice la redirección al feed

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /************ Verificación (Assert) ************/
        // PASO 7: Se obtiene la URL actual después del clic en Iniciar Sesión
        // PASO 8: Se imprime la URL en consola para depuración

        String urlActual = driver.getCurrentUrl();

        System.out.println(
                "URL actual después del login: "
                        + urlActual
        );

        // PASO 9: Se verifica que la URL actual sea exactamente el feed principal
        // Si la URL no coincide, la prueba falla indicando que el login no fue exitoso

        Assert.assertEquals(
                "http://localhost:8090/feed",
                urlActual
        );
        
        // Si se llega a este punto, el login fue exitoso
    }
}