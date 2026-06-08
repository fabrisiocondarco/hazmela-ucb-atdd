import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;

/****************************************/
// Historia de Usuario:
//
// Como usuario aceptar la ayuda ofrecida por un usuario
//
// Prueba de Aceptación:
//
// Verificar que se pueda aceptar la ayuda ofrecida por un usuario
//, 
//
// CASO BASE: Aceptar ayuda de otros usuarios #48
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Perfil
// 3. Seleccionar la opción "ver más" de una publicación
// 4. Seleccionar la opción "aceptar ayuda"
//
// Resultado Esperado:
//
// Aparece el chat con el ayudante
/****************************************/

public class AceptarAyudaTest extends BaseTest {

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
                .sendKeys("a@tes4t.edu");

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
        
        // PASO 5: Se localiza el anuncio específico que debería aparecer
        // Se mesutra la información de la publicación seleccionada

        WebElement vermas =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[3]/div/div/div[3]/div/a/button")
                );

        vermas.click();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // PASO 6: Se ingresa a la publicación y se selecciona el botón para aceptar ayuda
        WebElement aceptarayuda =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[1]")
                );

            aceptarayuda.click();
             try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            /********** Verificación **********/
        // PASO 7: Se verifica que aparezca el chat con el ayudante
        
        WebElement verificarayuda =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div/div[1]/h3")
                );
        
        Assert.assertEquals(true, verificarayuda.isDisplayed());

        // Se imprime en consola la confirmación de que se encontró el anuncio filtrado

        System.out.println(
                "Se aceptó la ayuda de manera correcta: "
                        + verificarayuda.isDisplayed()
        );
    }
}