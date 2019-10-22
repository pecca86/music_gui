import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
	private JButton flagButton;
	
	// GRADING BUTTONS:
	private JButton grade1;
	private JButton grade2;
	private JButton grade3;
	private JButton grade4;
	private JButton grade5;
	private JButton rmGrade;

	
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
		
		flagButton = createFlagButton();
		toolbar.add(flagButton);
		
		//GRADING BUTTONS:
		Label rateLabel = new Label("Grade the song:");
		toolbar.add(rateLabel);
		
		grade1 = createGrade1();
		toolbar.add(grade1);
		
		grade2 = createGrade2();
		toolbar.add(grade2);
		
		grade3 = createGrade3();
		toolbar.add(grade3);
		
		grade4 = createGrade4();
		toolbar.add(grade4);
		
		grade5 = createGrade5();
		toolbar.add(grade5);
		
		rmGrade = createRmGrade();
		toolbar.add(rmGrade);
		
		
		
		
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
		ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton(newAlbumIcon);
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
					controller.addNewAlbum();
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton(deleteAlbumIcon);
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
		ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
		JButton addSoundClipButton = new JButton(addSoundClipsIcon);
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
		ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton(removeSoundClipsIcon);
		//JButton removeSoundClipsButton = new JButton("Remove Sound Clips");
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
		ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton(playIcon);
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
	        ImageIcon undoIcon = new ImageIcon("icons/Actions-blue-arrow-undo-icon.png");
	        JButton undoButton = new JButton(undoIcon);
	        undoButton.setToolTipText("Undo recent action");
	        undoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                controller.undoAction();
	            }
	        });
	        return undoButton;
	    }
	   
	   
	   private JButton createFlagButton() {
	        ImageIcon flagIcon = new ImageIcon("icons/Actions-flag-icon.png");
	        JButton flagButton = new JButton(flagIcon);
	        flagButton.setToolTipText("Flag a song");
	        flagButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                controller.setFlag();
	            }
	        });
	        return flagButton;
	    }
	   
	   
	   private JButton createGrade1() {
			JButton rateButton = new JButton("1");
			
			rateButton.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					controller.setGrade(1);
				}
			});
			
			return rateButton;
	   }

		
		private JButton createGrade2() {
			JButton rateButton = new JButton("2");
			
			rateButton.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					controller.setGrade(2);
				}
			});
			
			return rateButton;
		}
		
		private JButton createGrade3() {
			JButton rateButton = new JButton("3");
			
			rateButton.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					controller.setGrade(3);
				}
			});
			
			return rateButton;
		}
		
		private JButton createGrade4() {
			JButton rateButton = new JButton("4");
			
			rateButton.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					controller.setGrade(4);
				}
			});
			
			return rateButton;
		}
		
		private JButton createGrade5() {
			JButton rateButton = new JButton("5");
			
			rateButton.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					controller.setGrade(5);
				}
			});
			
			return rateButton;
		}
		
		public JButton createRmGrade() {
			JButton rmButton = new JButton("Remove grading");
			
			rmButton.addActionListener(new ActionListener() {
				@Override
	            public void actionPerformed(ActionEvent e) {
					controller.setGrade(-1);
				}
			});
			
			return rmButton;
		}
}
