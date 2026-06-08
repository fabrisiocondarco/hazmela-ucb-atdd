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
// Verificar que se filtran los anuncios por tags seleccionados en el panel de filtros avanzados
//
// CASO BASE: Verificar que los tags disponibles 
// se muestran como badges clickeables y filtran el feed al seleccionarlos #71
//
// PASOS:
//
// 1. Iniciar sesión
// 2. Ir al Feed
// 3. Seleccionar la opción un tag específico en el panel 
//
// Resultado Esperado:
//
// Se muestran las publicaciónes con el tag seleccionado
/****************************************/

public class FiltrarTagsTest extends BaseTest {

    @Test
    public void verificarTags() {

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

        /********** Filtros **********/
        // PASO 4: Se localiza la lista de tags disponibles 
        // Se selecciona el tag con el texto "Matematicas" para filtrar el feed por ese tag

        WebElement tag=
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[2]/div[2]")
                );

        tag.click();

        // Se espera 8 segundos para que el filtro se aplique en tiempo real
        // y la lista de anuncios se actualice mostrando solo los que coinciden

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /********** Verificación **********/
        // PASO 5: Se verificará que las publicaciones que aparecen tengan el tag seleccionado

        WebElement verificarTag =
                driver.findElement(
                        By.xpath("//*[@id=\"root\"]/div[2]/div/div[3]/div/div/div[2]/div/span[1]")
                );
        boolean TagCorrecto = verificarTag.getText().contains("Matemáticas");
        Assert.assertEquals(true, TagCorrecto);


        // Se imprime en consola la confirmación de que se aplicó el filtro correcto

        System.out.println(
                "Se muestra las publicaciones con el tag seleccionado : "
                        + TagCorrecto
        );
    }
}