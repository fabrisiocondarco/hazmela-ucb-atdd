import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;

/****************************************/
// Historia de Usuario:
//
// Como usuario ver mis conversaciones en el módulo de Chat
//
// Prueba de Aceptación:
//
// Verificar que el módulo de Chat solo muestra conversaciones vinculadas a una solicitud de ayuda aceptada
//
// CASO BASE: Chat solo muestra conversaciones vinculadas a una solicitud de ayuda aceptada #16
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Feed
// 3. Seleccionar la opción "mensajes" 
//
// Resultado Esperado:
//
// Se muestra la lista de chats del usuario
/****************************************/

public class VerChatTest extends BaseTest {

    @Test
    public void verificarChat() {

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
                .sendKeys("a@test5.edu");

        // PASO 2: Se localiza el campo de contraseña por su ID "password"
        // Se ingresa la contraseña correcta asociada al correo

        driver.findElement(By.id("password"))
                .sendKeys("12345678Ab");

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

        /********** Mensajes **********/
        // PASO 4: Se localiza el boton mensajes mediante XPath
        // El campo contiene el placeholder "Mensajes"
        // Se selecciona la opción "Mensajes"

        WebElement mensajes =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/nav/div/div/a[3]/button")
                );

        mensajes.click();

        // Se espera 8 segundos para que el filtro se aplique en tiempo real
        // y la lista de anuncios se actualice mostrando solo los que coinciden

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Verificación **********/
        // PASO 5: Se localiza el chat específico que debería aparecer
        // Se verificará la presencia del chat correspondiente a la solicitud de ayuda aceptada

        WebElement verificarlista =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[1]/div[2]/button/div")
                );
        boolean AyudaEnviada = verificarlista.getText().contains("Alan Brito");
        Assert.assertEquals(true, AyudaEnviada);
        

        // Se imprime en consola la confirmación de que se encontró el chat 

        System.out.println(
                "Se encontró el chat : "
                        + verificarlista.isDisplayed()
        );
    }
}