import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/****************************************/
// Historia de Usuario:
//
// Como estudiante quiero que el sistema
// valide las reglas de contraseña
// antes de permitir el registro.
//
// Prueba de Aceptación:
//
// Verificar que no permita contraseñas
// menores de 8 caracteres y sin los
// requisitos mínimos.
//
// PASOS:
//
// 1. Ingresar a la página de registro
// 2. Completar el formulario
// 3. Escribir una contraseña inválida
// 4. Presionar Registrarse
//
// Resultado Esperado:
//
// Debe mostrarse el mensaje:
//
// "La contraseña no cumple los requisitos."
/****************************************/

public class PasswordInvalidaTest extends BaseTest {

    @Test
    public void verificarPasswordInvalida() {

        /********** PREPARACION DE LA PRUEBA **********/

        driver.get("http://localhost:8090/register");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** LOGICA DE LA PRUEBA **********/

        // PASO 1: Completar nombre

        WebElement nombre =
                driver.findElement(By.id("name"));

        nombre.sendKeys("Fabricio Prueba");

        // PASO 2: Completar correo institucional

        WebElement correo =
                driver.findElement(By.id("email"));

        correo.sendKeys("fabricio@ucb.edu.bo");

        // PASO 3: Completar carrera

        WebElement carrera =
                driver.findElement(By.id("career"));

        carrera.sendKeys("Ingenieria Informatica");

        // PASO 4: Ingresar contraseña inválida

        WebElement password =
                driver.findElement(By.id("password"));

        password.sendKeys("abc");

        // PASO 5: Confirmar contraseña inválida

        WebElement confirmPassword =
                driver.findElement(By.id("confirmPassword"));

        confirmPassword.sendKeys("abc");

        // PASO 6: Presionar botón Registrarse

        WebElement botonRegistro =
                driver.findElement(
                        By.xpath("//button[@type='submit']")
                );

        botonRegistro.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** VERIFICACION DEL RESULTADO ESPERADO **********/

        WebElement mensajeError =
                driver.findElement(
                        By.xpath(
                                "//*[contains(.,'La contraseña no cumple los requisitos.')]"
                        )
                );

        Assert.assertEquals(
                true,
                mensajeError.isDisplayed()
        );

        System.out.println(
                "Se muestra el mensaje de contraseña inválida: "
                        + mensajeError.isDisplayed()
        );
    }
}