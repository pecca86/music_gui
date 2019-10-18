import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MusicOrganizerButtonPanel extends JPanel {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private MusicOrganizerController controller;
	private MusicOrganizerWindow view;
	
	private JButton newAlbumButton;
	private JButton deleteAlbumButton;
	private JButton addSoundClipsButton;
	private JButton removeSoundClipsButton;	
	private JButton playButton;
	private JButton undoButton;
	private JButton redoButton;

	
	/**
	 * @param contr controller
	 * @param view view
	 */
	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view){
		super(new BorderLayout());

		controller = contr;
		
		this.view = view;
		
		JToolBar toolbar = new JToolBar();
		
		newAlbumButton = createNewAlbumButton();
		toolbar.add(newAlbumButton);

		deleteAlbumButton = createDeleteAlbumButton();
		toolbar.add(deleteAlbumButton);

		addSoundClipsButton = createAddSoundClipsButton();
		toolbar.add(addSoundClipsButton);

		removeSoundClipsButton = createRemoveSoundClipsButton();
		toolbar.add(removeSoundClipsButton);

		playButton = createPlayButton();
		toolbar.add(playButton);
		
		undoButton = createUndoButton();
		toolbar.add(undoButton);
		
		redoButton = createRedoButton();
		toolbar.add(redoButton);
		
		this.add(toolbar);

	}
	
	/**
	 * Note: You can replace the text strings in the instantiations of the JButtons
	 * below with ImageIcons if you prefer to have buttons with icons instead of
	 * buttons with text strings
	 * 
	 *  Example:
	 *  ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
	 *  JButton newAlbumButton = new JButton(newAlbumIcon);
	 *  
	 *  will put the imageIcon on the button, instead of the text "New Album", as 
	 *  done below
	 * 
	 */
	
	
	private JButton createNewAlbumButton() {
		//ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton("New Album");
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				controller.addNewAlbum(view.getSelectedAlbum());
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		//ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton("Remove Album");
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				controller.deleteAlbum();
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
		//ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
		JButton addSoundClipButton = new JButton("Add Sound Clips");
		addSoundClipButton.setToolTipText("Add Selected Sound Clips To Selected Album");
		addSoundClipButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {	
				controller.addSoundClips();

			}
		});
		return addSoundClipButton;
	}
	
	private JButton createRemoveSoundClipsButton() {
		//ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton("Remove Sound Clips");
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				controller.removeSoundClips();
			}
		});
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		//ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton("Play");
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
			}
		});
		return playButton;
	}
	
	
	   private JButton createUndoButton() {
	        //ImageIcon playIcon = new ImageIcon("icons/play_32.png");
	        JButton undoButton = new JButton("Undo");
	        undoButton.setToolTipText("Undo recent action");
	        undoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                controller.undoAction();
	            }
	        });
	        return undoButton;
	    }
	   
	   
       private JButton createRedoButton() {
           //ImageIcon playIcon = new ImageIcon("icons/play_32.png");
           JButton redoButton = new JButton("Redo");
           redoButton.setToolTipText("Redoes recent undone action");
           redoButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   controller.redoAction();
               }
           });
           return redoButton;
       }

}