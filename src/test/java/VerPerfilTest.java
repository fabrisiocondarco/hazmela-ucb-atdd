import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;

/****************************************/
// Historia de Usuario:
//
// Como usuario ver la información
// de mi perfil
//
// Prueba de Aceptación:
//
// Verificar que un usuario pueda ver la información de su perfil
//
// CASO BASE: Campo de búsqueda filtra anuncios por título en tiempo real #64
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Feed
// 3. Seleccionar la opción "perfil" 
//
// Resultado Esperado:
//
// Se muestra la información del perfil del usuario
/****************************************/

public class VerPerfilTest extends BaseTest {

    @Test
    public void verificarInformacionPerfil() {

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

        /********** Perfil **********/
        // PASO 4: Se localiza el boton perfil mediante XPath
        // El campo contiene el placeholder "Perfil"
        // Se selecciona la opción "Perfil"

        WebElement perfil =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/nav/div/div/a[4]/button")
                );

        perfil.click();

        // Se espera 8 segundos para que el filtro se aplique en tiempo real
        // y la lista de anuncios se actualice mostrando solo los que coinciden

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Verificación **********/
        // PASO 5: Se localiza el perfil específico que debería aparecer
        // Se mesutra la información de la persona
        // Se verificará la aparición del nombre del usuario

        WebElement perfilinfo =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[1]/div[1]/div/div[2]/h3")
                );

        // PASO 6: Se verifica que el perfil filtrado sea visible en la pantalla
        // Si el perfil no aparece, la prueba falla

        Assert.assertEquals(true, perfilinfo.isDisplayed());

        // Se imprime en consola la confirmación de que se encontró el perfil 

        System.out.println(
                "Se encontró el perfil : "
                        + perfilinfo.isDisplayed()
        );
    }
}