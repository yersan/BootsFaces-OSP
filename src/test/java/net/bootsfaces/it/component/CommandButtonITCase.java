/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.it.component;

import java.io.IOException;
import net.bootsfaces.it.IntegrationTestsBase;
import static net.bootsfaces.it.IntegrationTestsBase.createBaseDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author yersan
 */
@RunWith(Arquillian.class)
public class CommandButtonITCase extends IntegrationTestsBase {
    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive deployment = createBaseDeployment()
                .addAsWebResource("commandButton.xhtml");

        
        System.out.println(deployment.toString(true));

        return deployment;
    }

    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/commandButton.xhtml");
    }
    
    
    @Test
    @InSequence(1)
    public void testCustomImage() {
        String pageTitle = browser.getTitle();
        
        //assert page title
        assertEquals("CommandButton IT", pageTitle);
    }
}
