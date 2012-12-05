/*   Grenouilloland is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package grenouilloland.vue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;

/**
 * Cette classe permet de représenter l'action de l'application "Quitter"
 *
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
class ActionQuitter extends ActionAbstraite {

    /**
     * Instancie l'action Quitter.
     * 
     * @param vue La vue possédant l'action quitter
     */
    public ActionQuitter(final Vue vue){
        super(nom, icone, bulleAide, mnemonique, accelerateur, vue);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e){
        dernierArretAvantSortie();
    }
    
    /**
     * Méthode appelée avant fermeture de l'application. Permet de 
     * quitter proprement la JVM.
     */
    protected void dernierArretAvantSortie(){
        System.exit(0);
    }
    
    /** Le chemin pour accéder à l'icône */
    protected static final String cheminIcone = 
    "ressources/images/menu/quitter.png";
    /** L'icone de l'action quitter */
    protected static final Icon icone;
    static{
        ClassLoader loader = ActionQuitter.class.getClassLoader();
        icone = new ImageIcon(loader.getResource(cheminIcone));
    }
    
    /** Le nom de l'action */
    protected static final String nom = "Quitter";
    
    /** La bulle d'aide du menu */
    protected static final String bulleAide = 
    "Arrête la partie en cours et quitte l'application";
    
    /** Le mnemonique de l'action */
    protected static final int mnemonique = KeyEvent.VK_Q;
    
    /** L'accelerateur de l'action quitter */
    protected static final KeyStroke accelerateur = 
    KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK);
}

