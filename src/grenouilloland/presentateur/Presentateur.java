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
package grenouilloland.presentateur;

import grenouilloland.modele.Modele;
import grenouilloland.vue.Vue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Le présentateur permet de faire le lien entre la vue et le modèle de l'application.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.1
 */
public class Presentateur{
    
    /**
     * Instancie le présentateur. Crée le modèle et la vue du jeu.
     * 
     * @param resolutionMin La résolution minimale de la mare
     * @param resolutionMax La résolution maximale de la mare
     * @param resolution La résolution de la mare à créer
     */
    public Presentateur(final int resolutionMin, final int resolutionMax, final int resolution){
        modele = new Modele(resolution);
        
        this.resolutionMin = resolutionMin;
        this.resolutionMax = resolutionMax;
        
        timer = new Timer();
        
        vue = new Vue(this);
    }
    
    
    /**
     * Change le modèle de l'application.
     * 
     * @param resolution La résolution du modèle
     */
    public void nouveauModele(final int resolution){
        modele = new Modele(resolution);
    }
    
    /**
     * Deplace la grenouille à la position spécifiée
     * 
     * @param ligne La ligne où veut aller la grenouille
     * @param colonne La colonne où veut aller la grenouille
     */
    public void deplacerGrenouille(final int ligne, final int colonne){
        modele.deplacerGrenouille(ligne, colonne);
    }
    
    /**
     * Réinitialise le modèle ainsi que le chronomètre.
     */
    public void reinitialiser(){
         modele.reinitialiser();
         timer.cancel();
         vue.mettreAJour();
    }
    
    /**
     * Affiche l'interface graphique à l'écran (rend la fenêtre visible).
     */
    public void demarrer(){
        vue.pack();
        vue.setLocationRelativeTo(null);
        vue.setVisible(true);
    }
    
    /**
     * Permet d'executer le vieillissement des nenuphars chaque seconde.
     * L'action de démarrer une partie réinitialise au préalable le 
     * modèle (crée une nouvelle partie).
     */
    public void demarrerPartie(){
        reinitialiser();
        timer = new Timer();
        TimerTask tache = new TimerTask() { 
            @Override
                public void run() {
                    // La partie est finie, gagnée ou non, donc on
                    // arrête le timer.
                    if(modele.estTerminee()){
                        timer.cancel();
                        vue.mettreAJour();
                        vue.afficherFin();
                        return;
                    }
                    // Sinon on fait vieillir la mare
                    modele.genererEtapeSuivante();
                    vue.mettreAJour();
                }
        };
        // création et spécification de la tache à effectuer
        timer.scheduleAtFixedRate(tache, 0, 1000);
    }
    
    /**
     * @return La résolution maximale de la mare
     */
    public int lireResolutionMax(){
        return resolutionMax;
    }
    

    
    /**
     * @return La résolution minimale de la mare
     */
    public int lireResolutionMin(){
        return resolutionMin;
    }
    
    /**
     * @return Le modèle de l'application
     */
    public Modele lireModele(){
        return modele;
    }
    
    /** Timer permettant d'effectuer les actions chaque seconde */
    protected Timer timer;
    
    /** Le modèle de l'application */
    protected Modele modele;
    
    /** La résolution minimale de la mare */
    protected final int resolutionMin;
    
    /** La résolution maximale de la mare */
    protected final int resolutionMax;
    
    /** La vue de l'application */
    protected Vue vue;
}

