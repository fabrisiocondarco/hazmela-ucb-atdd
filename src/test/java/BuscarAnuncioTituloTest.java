import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/****************************************/
// Historia de Usuario:
//
// Como usuario quiero buscar anuncios
// por título en tiempo real.
//
// Prueba de Aceptación:
//
// Verificar que el buscador filtre
// anuncios por título.
//
// CASO BASE: Campo de búsqueda filtra anuncios por título en tiempo real #64
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Feed
// 3. Escribir una palabra clave
// 4. Verificar el anuncio filtrado
//
// Resultado Esperado:
//
// Solo aparece el anuncio que contiene
// la palabra buscada. La lista se filtra
// en tiempo real mientras se escribe.
/****************************************/

public class BuscarAnuncioTituloTest extends BaseTest {

    @Test
    public void verificarBusquedaAnuncios() {

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
                .sendKeys("fabrisio.condarco@ucb.edu.bo");

        // PASO 2: Se localiza el campo de contraseña por su ID "password"
        // Se ingresa la contraseña correcta asociada al correo

        driver.findElement(By.id("password"))
                .sendKeys("Prueba123@");

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
        // PASO 4: Se localiza el campo de búsqueda mediante XPath
        // El campo contiene el placeholder "Buscar"
        // Se escribe la palabra clave "Ayuda" para filtrar anuncios

        WebElement buscador =
                driver.findElement(
                        By.xpath("//input[contains(@placeholder,'Buscar')]")
                );

        buscador.sendKeys("Ayuda");

        // Se espera 8 segundos para que el filtro se aplique en tiempo real
        // y la lista de anuncios se actualice mostrando solo los que coinciden

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Verificación **********/
        // PASO 5: Se localiza el anuncio específico que debería aparecer
        // El anuncio tiene el título "Ayuda porfavor no se que hacer porfavor"
        // Este título contiene la palabra clave buscada "Ayuda"

        WebElement anuncio =
                driver.findElement(
                        By.xpath("//*[contains(text(),'Ayuda porfavor no se que hacer porfavor')]")
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