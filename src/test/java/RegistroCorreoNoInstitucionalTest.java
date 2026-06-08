import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/****************************************/
// Historia de Usuario:
//
// Como estudiante quiero registrarme en
// Hazmela UCB utilizando un correo
// institucional válido.
//
// Prueba de Aceptación:
//
// Verificar que el sistema rechace
// correos que no tengan dominio .edu
//
// PASOS:
//
// 1. Ingresar a la página de registro
// 2. Completar el formulario
// 3. Escribir un correo Gmail
// 4. Presionar Registrarse
//
// Resultado Esperado:
//
// Debe mostrarse el mensaje:
//
// "Debes usar un correo institucional (.edu)."
/****************************************/

public class RegistroCorreoNoInstitucionalTest extends BaseTest {

    @Test
    public void verificarCorreoNoInstitucional() {

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

        // PASO 2: Completar correo no institucional

        WebElement correo =
                driver.findElement(By.id("email"));

        correo.sendKeys("fabricio@gmail.com");

        // PASO 3: Completar carrera

        WebElement carrera =
                driver.findElement(By.id("career"));

        carrera.sendKeys("Ingenieria Informatica");

        // PASO 4: Completar contraseña

        WebElement password =
                driver.findElement(By.id("password"));

        password.sendKeys("Password123");

        // PASO 5: Confirmar contraseña

        WebElement confirmPassword =
                driver.findElement(By.id("confirmPassword"));

        confirmPassword.sendKeys("Password123");

        // PASO 6: Presionar botón Registrarse

        WebElement botonRegistro =
                driver.findElement(
                        By.xpath("//button[@type='submit']")
                );

        botonRegistro.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** INFORMACION DE DEPURACION **********/

        System.out.println("====================================");
        System.out.println("URL ACTUAL:");
        System.out.println(driver.getCurrentUrl());
        System.out.println("====================================");

        System.out.println("====================================");
        System.out.println("TEXTO COMPLETO DE LA PAGINA:");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        System.out.println("====================================");

        /********** VERIFICACION DEL RESULTADO ESPERADO **********/

        WebElement mensajeError =
                driver.findElement(
                        By.xpath(
                                "//*[contains(.,'Debes usar un correo institucional (.edu).')]"
                        )
                );

        Assert.assertEquals(
                true,
                mensajeError.isDisplayed()
        );

        System.out.println(
                "Se muestra el mensaje de error: "
                        + mensajeError.isDisplayed()
        );
    }
}