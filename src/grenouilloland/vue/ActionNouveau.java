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

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;

/**
 * Cette classe permet de représenter l'action du menu "Quitter"
 *
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.0
 */
class ActionNouveau extends ActionAbstraite {

    /**
     * Instancie l'action Nouvelle partie.
     * 
     * @param vue La vue contenant l'action à propos
     */
    public ActionNouveau(final Vue vue){
        super(nom, icone, bulleAide, mnemonique, accelerateur, vue);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e){
        lireVue().cbReinitialiser();
    }
    
    /** Le chemin pour accéder à l'icône */
    protected static final String cheminIcone = 
    "ressources/images/menu/nouveau.png";
    
    /** L'icone de l'action nouveau */
    protected static final Icon icone;
    static{
        ClassLoader loader = ActionNouveau.class.getClassLoader();
        icone = new ImageIcon(loader.getResource(cheminIcone));
    }
    /** Le nom de l'action */
    protected static final String nom = "Nouvelle partie";
    
    /** La bulle d'aide de l'action */
    protected static final String bulleAide = 
    "Prépare une nouvelle partie";
    
    /** Le mnemonique de l'action */
    protected static final int mnemonique = KeyEvent.VK_N;
    
    /** L'accelerateur de l'action nouvelle partie */
    protected static final KeyStroke accelerateur =
    KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
}
