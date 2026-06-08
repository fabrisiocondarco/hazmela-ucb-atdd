import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;

/****************************************/
// Historia de Usuario:
//
// Como usuario que se puedan aplicar filtros a la busqueda de publicaciones en el Feed
//
// Prueba de Aceptación:
//
// Verificar que el botón "Filtros" abre el panel de filtros avanzados
//
// CASO BASE: Verificar que el botón "Filtros" abre y cierra el panel de filtros avanzados #68
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Feed
// 3. Seleccionar la opción "Filtros" 
//
// Resultado Esperado:
//
// Se muestra el panel de filtros avanzados
/****************************************/

public class VerFiltrosTest extends BaseTest {

    @Test
    public void verificarPanelFiltros() {

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

        /********** Filtros **********/
        // PASO 4: Se localiza el boton filtros mediante XPath
        // El campo contiene el placeholder "Filtros"
        // Se selecciona la opción "Filtros"

        WebElement filtros =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[1]/button/span")
                );

        filtros.click();

        // Se espera 8 segundos para que el filtro se aplique en tiempo real
        // y la lista de anuncios se actualice mostrando solo los que coinciden

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Verificación **********/
        // PASO 5: Se localiza el panel de filtros que debería aparecer
        // Se verifica que el panel de filtros esté visible
        // Se verificará la aparición del panel de filtros

        WebElement panelFiltros =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[2]")
                );

        // PASO 6: Se verifica que el aparezca el panel de filtros
        // Si el panel no aparece, la prueba falla

        Assert.assertEquals(true, panelFiltros.isDisplayed());

        // Se imprime en consola la confirmación de que se encontró el panel de filtros

        System.out.println(
                "Se muestra el panel de filtros : "
                        + panelFiltros.isDisplayed()
        );
    }
}