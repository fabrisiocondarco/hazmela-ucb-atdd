import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;

/****************************************/
// Historia de Usuario:
//
// Como usuario ver la información
// de una publicaión
//
// Prueba de Aceptación:
//
// Verificar que cuando un usuario realice una publicación
//, los demás usuarios puedan ver su información
//
// CASO BASE: Verificar que cuando un usuario realice una publicación,
//  los demás usuarios puedan ver su información #46
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Feed
// 3. Seleccionar la opción "ver más" de una publicación
//
// Resultado Esperado:
//
// Se muestra la información de la publicación seleccionada
/****************************************/

public class IngresarAnuncioTest extends BaseTest {

    @Test
    public void verificarInformacionAnuncios() {

        /********** Preparación **********/
        // Se navega a la página de login del sitio web
        // Se espera 3 segundos para que cargue el formulario

        driver.get("http://localhost:8090/login");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Login **********/
        // PASO 1: Se localiza el campo de correo por su ID "email"
        // Se ingresa un correo institucional válido y registrado

        driver.findElement(By.id("email"))
                .sendKeys("sebastian.orias@ucb.edu.bo");

        // PASO 2: Se localiza el campo de contraseña por su ID "password"
        // Se ingresa la contraseña correcta asociada al correo

        driver.findElement(By.id("password"))
                .sendKeys("8431165So");

        // PASO 3: Se localiza el botón de submit mediante XPath
        // Se hace clic para enviar el formulario de login

        driver.findElement(By.xpath("//button[@type='submit']"))
                .click();

        // Se espera 4 segundos para que el sistema procese el login
        // y redirija al feed (panel de exploración)

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Buscar **********/
        // PASO 4: Se localiza el boton ver mas de una publiacion mediante XPath
        // El campo contiene el placeholder "Ver mas"
        // Se selecciona la opción "Ver mas"

        WebElement vermas =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[3]/div/div/div[3]/div/a/button")
                );

        vermas.click();

        // Se espera 8 segundos para que el filtro se aplique en tiempo real
        // y la lista de anuncios se actualice mostrando solo los que coinciden

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Verificación **********/
        // PASO 5: Se localiza el anuncio específico que debería aparecer
        // Se mesutra la información de la publicación seleccionada

        WebElement anuncio =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[1]/div/div/h3")
                );

        // PASO 6: Se verifica que el anuncio filtrado sea visible en la pantalla
        // Si el anuncio no aparece, la prueba falla

        Assert.assertEquals(true, anuncio.isDisplayed());

        // Se imprime en consola la confirmación de que se encontró el anuncio filtrado

        System.out.println(
                "Se encontró el anuncio filtrado: "
                        + anuncio.isDisplayed()
        );
    }
}