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

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

import grenouilloland.presentateur.Presentateur;
import grenouilloland.modele.Modele;

/**
 * Cette classe permet de représenter la vue de l'application.<br />
 * La vue contient plusieurs widgets représentant les contrôles et 
 * l'affichage du modèle.
 * 
 * @author Louis-Axel AMBROISE
 * @author Thibault LEFRANCOIS
 * @version 1.1
 */
public class Vue extends JFrame {
    
    /**
     * Instancie la vue.
     * 
     * @param presentateur Le presentateur ayant instancié la vue
     */
    public Vue(final Presentateur presentateur){
        super(titre);
        this.presentateur = presentateur;
        
        actionQuitter = new ActionQuitter(this);
        
        contruireBarreMenusEtOutils();
        construirePanneauxControle();
        
        modeleGraphique = new ModeleGraphique(this);
        modeleGraphique.mettreAJour();

        add(modeleGraphique, BorderLayout.CENTER);
        
        // On appelle dernierArretAvantSortie lorsque l'on veut fermer la fenêtre.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                actionQuitter.dernierArretAvantSortie();
            }
        });    
        
    }
    
    /**
     * @return Le presentateur de l'application
     */
    public Presentateur lirePresentateur(){
            return presentateur;
    }
    
    /**
     * @return Le modèle graphique de l'application
     */
    public ModeleGraphique lireModeleGraphique(){
            return modeleGraphique;
    }
    
    /**
     * Met à jour tous les composants de la vue
     */
    public void mettreAJour(){
        modeleGraphique.mettreAJour();
        chronometre.mettreAJour();
        panneauControle.mettreAJour();
    }
    
    /**
     * Affiche le message de fin de partie, qui peut être gagnante ou 
     * perdante.
     */
    public void afficherFin(){
        if(presentateur.lireModele().estGagnant()){
            JOptionPane.showMessageDialog(this, texteGagnant, titre, 
                                      JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, textePerdant, titre, 
                                      JOptionPane.INFORMATION_MESSAGE);
        }
        cbReinitialiser(); // On prépare une nouvelle partie.
    }
    
    /**
     * Construit la barre de menu ainsi que la barre d'outils de 
     * l'application.
     */
    protected void contruireBarreMenusEtOutils(){
        final JMenuBar barreMenu  = new JMenuBar();
        final JToolBar barreOutils = new JToolBar();
        final JMenu menu = new JMenu("Grenouilloland");
        final JSeparator separateur = new JSeparator();
        final ActionAbstraite actionAPropos = new ActionAPropos(this);
        final ActionAbstraite actionNouveau = new ActionNouveau(this);
        final ActionAbstraite actionLancer = new ActionLancer(this);
        
        menu.setMnemonic('g');
        // Ajout des différentes actions du menu
        menu.add(actionNouveau);
        menu.add(actionLancer);
        menu.add(actionAPropos);
        menu.add(separateur);
        menu.add(actionQuitter);
        // On ajoute le menu à la barre de menu
        barreMenu.add(menu);
        setJMenuBar(barreMenu);
        
        // Ajout des différentes actions de la barre d'outils
        barreOutils.add(actionNouveau);
        barreOutils.add(actionLancer);
        barreOutils.add(actionAPropos);
        barreOutils.addSeparator();
        barreOutils.add(actionQuitter);
        
        add(barreOutils, BorderLayout.NORTH);
    }    
    
    /**
     * Construit les panneaux de contrôle de l'application (points de 
     * vie, chronomètre et résolution)
     */
    protected void construirePanneauxControle(){
        // Ajout de la résolution
        final JPanel panneauBas = new JPanel();
        panneauBas.setLayout(new GridLayout(2,1));
        final Resolution resolution = new Resolution(
                                      JScrollBar.HORIZONTAL, this);
        chronometre = new Chronometre(this);
        final JPanel panneauReso = new JPanel(new GridLayout(1,1));
        final JPanel panneauChrono = new JPanel(new GridLayout(1,1));
        
        panneauReso.setBorder(BorderFactory.createTitledBorder( 
                " Résolution "));
        panneauChrono.setBorder(BorderFactory.createTitledBorder( 
                " Chronomètre "));
        panneauReso.add(resolution);
        panneauChrono.add(chronometre);
        panneauBas.add(panneauReso);
        panneauBas.add(panneauChrono);
        add(panneauBas, BorderLayout.SOUTH);
        
        // Ajout du panneau de contrôle
        panneauControle = new PanneauControle(this);
        add(panneauControle, BorderLayout.WEST);
    }
    
    /**
     * Fonction callback (synchronisée) permettant de créer un nouveau modèle
     * pour l'application en cours.<br/>
     * Le modèle ainsi crée doit être associé à un nouveau modèle graphique,
     * qui doit lui-même être ajouté à la vue.
     * 
     * @param resolution La résolution du nouveau modèle
     */
    protected synchronized void cbNouveauModele(final int resolution){
        presentateur.nouveauModele(resolution);
        remove(modeleGraphique);
        modeleGraphique = new ModeleGraphique(this);
        add(modeleGraphique, BorderLayout.CENTER);
        validate();
        mettreAJour();
    }
    
    /**
     * Fonction callback (synchronisée) permettant de déplacer la grenouille
     * sur la case spécifiée en paramètre
     * 
     * @param surface La surface où déplacer la grenouille
     */
    protected synchronized void cbDeplacerGrenouille(final SurfaceGraphique surface){
        int ligne = surface.lireLigne();
        int colonne = surface.lireColonne();
        presentateur.deplacerGrenouille(ligne, colonne);
        mettreAJour();
    }
    
    /**
     * Fonction callback (synchronisee) permettant de  lancer la partie
     */
    protected synchronized void cbDemarrerPartie(){
        presentateur.demarrerPartie();
    }
    
    /**
     * Fonction callback (synchronisée) permettant de réinitialiser le 
     * modèle afin de préparer une nouvelle partie
     */
    protected synchronized void cbReinitialiser(){
        presentateur.reinitialiser();
        modeleGraphique.mettreAJour();
        chronometre.mettreAJour();
    }
    
    /** Le titre de la fenêtre */
    protected static final String titre = "Grenouilloland - V1.1";
    
    /** Le texte affiché quand on gagne. */
    protected static final String texteGagnant = 
    "Félicitation, vous avez gagné !";
    
    /** Le texte affiché quand on perd */
    protected static final String textePerdant = 
    "Tu viens de tuer une grenouille. Recommence !";
    
    /** Le chronomètre permettant d'afficher le temps restant */
    protected Chronometre chronometre; 
    
    /** Le présentateur ayant instancié l'application */
    protected Presentateur presentateur;
    
    /** Le panneau de contrôle (affiche le nombre de points de vie) */
    protected PanneauControle panneauControle;
    
    /** L'action quitter, permettant de fermer proprement l'application */
    protected ActionQuitter actionQuitter;
    
    /** Le modèle graphique associé à cette vue */
    protected ModeleGraphique modeleGraphique;
}
