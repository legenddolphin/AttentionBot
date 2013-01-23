/***************************************************************************************
 This is a free software project.
 Copyright (C) 2012 by Yen-Chia Hsu, Carnegie Mellon University, U.S.A.
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ***************************************************************************************/

package hummingbeans;

import edu.cmu.ri.createlab.hummingbird.HummingbirdRobot;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JLabel;

/**
 *
 * @author Yen-Chia Hsu, Carnegie Mellon University, U.S.A.
 */
public class attentionBot extends javax.swing.JFrame {
    // Instantiate the Hummingbird object (establishes a connection to the Hummingbird)
    HummingbirdRobot sillyBird = new HummingbirdRobot();

    String wavPath = System.getProperty("user.dir") + "\\wav";
    String wavNiceMale = wavPath + "\\nice_male_Mike\\";
    String wavNiceFemale = wavPath + "\\nice_female_Crystal\\";
    String wavMeanMale = wavPath + "\\mean_male_Rich\\";
    String wavMeanFemale = wavPath + "\\mean_female_Claire\\";
    Clip clip;
    File greet1_wav;
    File greet2_wav;
    File askPlay_wav;
    File askNext_wav;
    File askWait_wav;
    File r1_game_wav;
    File r2_game_wav;
    File r3_game_wav;
    File r4_game_wav;
    File r4Again_game_wav;
    File r5_game_wav;
    File r5Again_game_wav;
    File r1_feedback_wav;
    File r2_feedback_wav;
    File r3_feedback_wav;
    File r4_feedback_wav;
    File r4Again_feedback_wav;
    File r5_feedback_wav;
    File r5Again_feedback_wav;
    File goNextState_wav;
    File resetGame_wav;
    int game = 0;
    int feedback = 0;
    int mode = 0;//1=nice, 2=mean

    /**
     * Creates new form attentionBot
     */
    public attentionBot() {
        initComponents();
        System.out.println("start");
        lockAllButtons();
        //set text box
        textBox.setVisible(true);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setText("<html>Welcome to the sound mimic game.<br>Just mimic what the robot says.<br><br>(Press the button to play the game.)</html>");
        //add audio event
        try {
            clip = AudioSystem.getClip();
            /*
            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                    }
                }
            });*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        unlockVoiceSelectButtons();
    }

    public void readFiles(String path) {
        try {
            greet1_wav = new File(path + "\\greet1.wav");
            greet2_wav = new File(path + "\\greet2.wav");
            askPlay_wav = new File(path + "\\askPlay.wav");
            askNext_wav = new File(path + "\\askNext.wav");
            askWait_wav = new File(path + "\\askWait.wav");
            r1_game_wav = new File(path + "\\r1_game.wav");
            r2_game_wav = new File(path + "\\r2_game.wav");
            r3_game_wav = new File(path + "\\r3_game.wav");
            r4_game_wav = new File(path + "\\r4_game.wav");
            r4Again_game_wav = new File(path + "\\r4Again_game.wav");
            r5_game_wav = new File(path + "\\r5_game.wav");
            r5Again_game_wav = new File(path + "\\r5Again_game.wav");
            r1_feedback_wav = new File(path + "\\r1_feedback.wav");
            r2_feedback_wav = new File(path + "\\r2_feedback.wav");
            r3_feedback_wav = new File(path + "\\r3_feedback.wav");
            r4_feedback_wav = new File(path + "\\r4_feedback.wav");
            r4Again_feedback_wav = new File(path + "\\r4Again_feedback.wav");
            r5_feedback_wav = new File(path + "\\r5_feedback.wav");
            r5Again_feedback_wav = new File(path + "\\r5Again_feedback.wav");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void unlockControlButtons() {
        goNextState.setEnabled(true);
        resetGame.setEnabled(true);
    }

    public void unlockVoiceSelectButtons() {
        niceMale.setEnabled(true);
        niceFemale.setEnabled(true);
        meanMale.setEnabled(true);
        meanFemale.setEnabled(true);
    }

    public void unlockGreetButtons() {
        greet1.setEnabled(true);
        greet2.setEnabled(true);
    }

    public void lockAllButtons() {
        niceMale.setEnabled(false);
        niceFemale.setEnabled(false);
        meanMale.setEnabled(false);
        meanFemale.setEnabled(false);
        greet1.setEnabled(false);
        greet2.setEnabled(false);
        askPlay.setEnabled(false);
        askNext.setEnabled(false);
        askWait.setEnabled(false);
        r1_game.setEnabled(false);
        r2_game.setEnabled(false);
        r3_game.setEnabled(false);
        r4_game.setEnabled(false);
        r4Again_game.setEnabled(false);
        r5_game.setEnabled(false);
        r5Again_game.setEnabled(false);
        r1_feedback.setEnabled(false);
        r2_feedback.setEnabled(false);
        r3_feedback.setEnabled(false);
        r4_feedback.setEnabled(false);
        r4Again_feedback.setEnabled(false);
        r5_feedback.setEnabled(false);
        r5Again_feedback.setEnabled(false);
        goNextState.setEnabled(false);
        resetGame.setEnabled(false);
    }

    public void playSound(File soundFile) {
        try {
            clip.close();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resetBox = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        resetNo = new javax.swing.JButton();
        resetYes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textBox = new javax.swing.JFrame();
        textLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        meanMale = new javax.swing.JButton();
        meanFemale = new javax.swing.JButton();
        niceFemale = new javax.swing.JButton();
        selectVoice_txt = new javax.swing.JLabel();
        niceMale = new javax.swing.JButton();
        greet1 = new javax.swing.JButton();
        greet2 = new javax.swing.JButton();
        greet_txt = new javax.swing.JLabel();
        ask_txt = new javax.swing.JLabel();
        askPlay = new javax.swing.JButton();
        askNext = new javax.swing.JButton();
        askWait = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        r5_game = new javax.swing.JButton();
        r5Again_game = new javax.swing.JButton();
        r3_game = new javax.swing.JButton();
        r4Again_game = new javax.swing.JButton();
        r4_game = new javax.swing.JButton();
        game_txt = new javax.swing.JLabel();
        r1_game = new javax.swing.JButton();
        r2_game = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        r5_feedback = new javax.swing.JButton();
        r5Again_feedback = new javax.swing.JButton();
        r3_feedback = new javax.swing.JButton();
        r4Again_feedback = new javax.swing.JButton();
        r4_feedback = new javax.swing.JButton();
        feedback_txt = new javax.swing.JLabel();
        r1_feedback = new javax.swing.JButton();
        r2_feedback = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        servoPan = new javax.swing.JSlider();
        servoPan_txt = new javax.swing.JLabel();
        servoCandy = new javax.swing.JSlider();
        servoCandy_txt = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        resetGame = new javax.swing.JButton();
        goNextState = new javax.swing.JButton();

        resetBox.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        resetBox.setAlwaysOnTop(true);
        resetBox.setLocationByPlatform(true);
        resetBox.setMinimumSize(new java.awt.Dimension(331, 156));
        resetBox.setResizable(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        resetNo.setText("No");
        resetNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetNoActionPerformed(evt);
            }
        });

        resetYes.setText("Yes");
        resetYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetYesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Are you sure to reset the robot?");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(resetYes, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resetNo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetYes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout resetBoxLayout = new javax.swing.GroupLayout(resetBox.getContentPane());
        resetBox.getContentPane().setLayout(resetBoxLayout);
        resetBoxLayout.setHorizontalGroup(
            resetBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resetBoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resetBoxLayout.setVerticalGroup(
            resetBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resetBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textBox.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        textBox.setFocusableWindowState(false);
        textBox.setLocationByPlatform(true);
        textBox.setMinimumSize(new java.awt.Dimension(800, 600));

        textLabel.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        textLabel.setForeground(new java.awt.Color(51, 51, 51));
        textLabel.setText("Mimic what the robot says.");
        textLabel.setToolTipText("");
        textLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout textBoxLayout = new javax.swing.GroupLayout(textBox.getContentPane());
        textBox.getContentPane().setLayout(textBoxLayout);
        textBoxLayout.setHorizontalGroup(
            textBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        textBoxLayout.setVerticalGroup(
            textBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        meanMale.setText("Mean Male");
        meanMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meanMaleActionPerformed(evt);
            }
        });

        meanFemale.setText("Mean Female");
        meanFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meanFemaleActionPerformed(evt);
            }
        });

        niceFemale.setText("Nice Female");
        niceFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niceFemaleActionPerformed(evt);
            }
        });

        selectVoice_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        selectVoice_txt.setText("Select Voice");

        niceMale.setText("Nice Male");
        niceMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niceMaleActionPerformed(evt);
            }
        });

        greet1.setText("Greet 1");
        greet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greet1ActionPerformed(evt);
            }
        });

        greet2.setText("Greet 2");
        greet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greet2ActionPerformed(evt);
            }
        });

        greet_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        greet_txt.setText("Greet");

        ask_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ask_txt.setText("Ask");

        askPlay.setText("Ask Play");
        askPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                askPlayActionPerformed(evt);
            }
        });

        askNext.setText("Ask Next");
        askNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                askNextActionPerformed(evt);
            }
        });

        askWait.setText("Ask Wait");
        askWait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                askWaitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meanMale, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meanFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(niceFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectVoice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(niceMale, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greet1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greet2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greet_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ask_txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(askPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(askNext, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(askWait, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {meanFemale, meanMale, niceFemale, niceMale, selectVoice_txt});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectVoice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(niceMale, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(niceFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meanMale, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meanFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(greet_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(greet1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greet2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ask_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(askPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(askNext, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(askWait, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {meanFemale, meanMale, niceFemale, niceMale});

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        r5_game.setText("R5-1 Game");
        r5_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r5_gameActionPerformed(evt);
            }
        });

        r5Again_game.setText("R5-2 Game");
        r5Again_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r5Again_gameActionPerformed(evt);
            }
        });

        r3_game.setText("R3 Game");
        r3_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r3_gameActionPerformed(evt);
            }
        });

        r4Again_game.setText("R4-2 Game");
        r4Again_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r4Again_gameActionPerformed(evt);
            }
        });

        r4_game.setText("R4-1 Game");
        r4_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r4_gameActionPerformed(evt);
            }
        });

        game_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        game_txt.setText("Game");

        r1_game.setText("R1 Game");
        r1_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1_gameActionPerformed(evt);
            }
        });

        r2_game.setText("R2 Game");
        r2_game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2_gameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r4Again_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r5_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r5Again_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r3_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r4_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r1_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(game_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r2_game, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(game_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r1_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r2_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r3_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r4_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r4Again_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r5_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r5Again_game, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        r5_feedback.setText("R5-1 Fb");
        r5_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r5_feedbackActionPerformed(evt);
            }
        });

        r5Again_feedback.setText("R5-2 Fb");
        r5Again_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r5Again_feedbackActionPerformed(evt);
            }
        });

        r3_feedback.setText("R3 Fb");
        r3_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r3_feedbackActionPerformed(evt);
            }
        });

        r4Again_feedback.setText("R4-2 Fb");
        r4Again_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r4Again_feedbackActionPerformed(evt);
            }
        });

        r4_feedback.setText("R4-1 Fb");
        r4_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r4_feedbackActionPerformed(evt);
            }
        });

        feedback_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        feedback_txt.setText("Feedback");

        r1_feedback.setText("R1 Fb");
        r1_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1_feedbackActionPerformed(evt);
            }
        });

        r2_feedback.setText("R2 Fb");
        r2_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2_feedbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r4Again_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r5_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r5Again_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r3_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r4_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r1_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feedback_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r2_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(feedback_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r1_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r2_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r3_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r4_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r4Again_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r5_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r5Again_feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        servoPan.setMaximum(255);
        servoPan.setPaintTicks(true);
        servoPan.setToolTipText("");
        servoPan.setValue(0);
        servoPan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        servoPan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                servoPanStateChanged(evt);
            }
        });

        servoPan_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        servoPan_txt.setText("Servo Pan");

        servoCandy.setMaximum(255);
        servoCandy.setPaintTicks(true);
        servoCandy.setToolTipText("");
        servoCandy.setValue(0);
        servoCandy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        servoCandy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                servoCandyStateChanged(evt);
            }
        });

        servoCandy_txt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        servoCandy_txt.setText("Servo Candy");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(servoCandy_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(servoCandy, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(servoPan_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(servoPan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(servoPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(servoCandy_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(servoPan_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(servoCandy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {servoCandy, servoPan});

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        resetGame.setText("Reset Game");
        resetGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetGameActionPerformed(evt);
            }
        });

        goNextState.setText("Go To Next State");
        goNextState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goNextStateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resetGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goNextState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(goNextState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetGame, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void servoCandyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_servoCandyStateChanged
        // Set the servo with the value directly - conveniently, servo also has a range of 0-255
        try {
            sillyBird.setServoPosition(1, servoCandy.getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_servoCandyStateChanged

    private void servoPanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_servoPanStateChanged
        try {
            sillyBird.setServoPosition(2, servoPan.getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_servoPanStateChanged

    private void niceMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niceMaleActionPerformed
        lockAllButtons();
        mode = 1;
        readFiles(wavNiceMale);
        unlockGreetButtons();
        unlockControlButtons();
        askPlay.setEnabled(true);
    }//GEN-LAST:event_niceMaleActionPerformed

    private void niceFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niceFemaleActionPerformed
        lockAllButtons();
        mode = 1;
        readFiles(wavNiceFemale);
        unlockGreetButtons();
        unlockControlButtons();
        askPlay.setEnabled(true);
    }//GEN-LAST:event_niceFemaleActionPerformed

    private void meanMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meanMaleActionPerformed
        lockAllButtons();
        mode = 2;
        readFiles(wavMeanMale);
        unlockGreetButtons();
        unlockControlButtons();
        askPlay.setEnabled(true);
    }//GEN-LAST:event_meanMaleActionPerformed

    private void meanFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meanFemaleActionPerformed
        lockAllButtons();
        mode = 2;
        readFiles(wavMeanFemale);
        unlockGreetButtons();
        unlockControlButtons();
        askPlay.setEnabled(true);
    }//GEN-LAST:event_meanFemaleActionPerformed

    private void greet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greet1ActionPerformed
        playSound(greet1_wav);
    }//GEN-LAST:event_greet1ActionPerformed

    private void greet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greet2ActionPerformed
        playSound(greet2_wav);
    }//GEN-LAST:event_greet2ActionPerformed

    private void askPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_askPlayActionPerformed
        playSound(askPlay_wav);
    }//GEN-LAST:event_askPlayActionPerformed

    private void askNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_askNextActionPerformed
        playSound(askNext_wav);
    }//GEN-LAST:event_askNextActionPerformed

    private void askWaitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_askWaitActionPerformed
        playSound(askWait_wav);
    }//GEN-LAST:event_askWaitActionPerformed

    private void r1_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1_gameActionPerformed
        playSound(r1_game_wav);
    }//GEN-LAST:event_r1_gameActionPerformed

    private void r2_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2_gameActionPerformed
        playSound(r2_game_wav);
    }//GEN-LAST:event_r2_gameActionPerformed

    private void r3_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r3_gameActionPerformed
        playSound(r3_game_wav);
    }//GEN-LAST:event_r3_gameActionPerformed

    private void r4_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r4_gameActionPerformed
        playSound(r4_game_wav);
    }//GEN-LAST:event_r4_gameActionPerformed

    private void r4Again_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r4Again_gameActionPerformed
        playSound(r4Again_game_wav);
    }//GEN-LAST:event_r4Again_gameActionPerformed

    private void r5_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r5_gameActionPerformed
        playSound(r5_game_wav);
    }//GEN-LAST:event_r5_gameActionPerformed

    private void r5Again_gameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r5Again_gameActionPerformed
        playSound(r5Again_game_wav);
    }//GEN-LAST:event_r5Again_gameActionPerformed

    private void r1_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1_feedbackActionPerformed
        playSound(r1_feedback_wav);
    }//GEN-LAST:event_r1_feedbackActionPerformed

    private void r2_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2_feedbackActionPerformed
        playSound(r2_feedback_wav);
    }//GEN-LAST:event_r2_feedbackActionPerformed

    private void r3_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r3_feedbackActionPerformed
        playSound(r3_feedback_wav);
    }//GEN-LAST:event_r3_feedbackActionPerformed

    private void r4_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r4_feedbackActionPerformed
        playSound(r4_feedback_wav);
    }//GEN-LAST:event_r4_feedbackActionPerformed

    private void r4Again_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r4Again_feedbackActionPerformed
        playSound(r4Again_feedback_wav);
    }//GEN-LAST:event_r4Again_feedbackActionPerformed

    private void r5_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r5_feedbackActionPerformed
        playSound(r5_feedback_wav);
    }//GEN-LAST:event_r5_feedbackActionPerformed

    private void r5Again_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r5Again_feedbackActionPerformed
        playSound(r5Again_feedback_wav);
    }//GEN-LAST:event_r5Again_feedbackActionPerformed

    private void goNextStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goNextStateActionPerformed
        if (game == 0 && feedback == 0) {
            lockAllButtons();
            game = 1;
            playSound(r1_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r1_game.setEnabled(true);
        } else if (game == 1 && feedback == 0) {
            lockAllButtons();
            feedback = 1;
            playSound(r1_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>Wow, you did great. Please take a candy.<br><br>(Press the button and go to the next round.)</html>");
            else if(mode==2)
                textLabel.setText("<html>That sounds OK. I waste a candy.<br><br>(Press the button and go to the next round.)</html>");
            unlockControlButtons();
            r1_feedback.setEnabled(true);
            askNext.setEnabled(true);
        } else if (game == 1 && feedback == 1) {
            lockAllButtons();
            game = 2;
            playSound(r2_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r2_game.setEnabled(true);
        } else if (game == 2 && feedback == 1) {
            lockAllButtons();
            feedback = 2;
            playSound(r2_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>That's great!! Your voice is nice. <br>Come, take a candy.<br><br>(Press the button and go to the next round.)</html>");
            else if(mode==2)
                textLabel.setText("<html>You are lucky. <br>But I will not give you candy. <br>HaHaHa!<br><br>(Press the button and go to the next round.)</html>");
            unlockControlButtons();
            r2_feedback.setEnabled(true);
            askNext.setEnabled(true);
        } else if (game == 2 && feedback == 2) {
            lockAllButtons();
            game = 3;
            playSound(r3_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r3_game.setEnabled(true);
        } else if (game == 3 && feedback == 2) {
            lockAllButtons();
            feedback = 3;
            playSound(r3_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>HaHaHa. That is happy. <br>Take a candy with you.<br><br>(Press the button and go to the next round.)</html>");
            else if(mode==2)
                textLabel.setText("<html>Nooooo. I lose a candy bar.<br><br>(Press the button and go to the next round.)</html>");            
            unlockControlButtons();
            r3_feedback.setEnabled(true);
            askNext.setEnabled(true);
        } else if (game == 3 && feedback == 3) {
            lockAllButtons();
            game = 4;
            playSound(r4_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r4_game.setEnabled(true);
        } else if (game == 4 && feedback == 3) {
            lockAllButtons();
            feedback = 4;
            playSound(r4_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>Oh, oh! Need some tweaking. Try it again.<br><br>(Press the button and go to the next round.)</html>");
            else if(mode==2)
                textLabel.setText("<html>Hey, not even close, OK? <br>Listen again carefully.<br><br>(Press the button and go to the next round.)</html>");            
            unlockControlButtons();
            r4_feedback.setEnabled(true);
            askNext.setEnabled(true);
        } else if (game == 4 && feedback == 4) {
            lockAllButtons();
            game = 5;
            playSound(r4Again_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r4Again_game.setEnabled(true);
        } else if (game == 5 && feedback == 4) {
            lockAllButtons();
            feedback = 5;
            playSound(r4Again_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>Congratulations. You made it. <br>Please have a candy.<br><br>(Press the button and go to the next round.)</html>");
            else if(mode==2)
                textLabel.setText("<html>Too bad. You Lose. Hahahahahahahaha.<br><br>(Press the button and go to the next round.)</html>");              
            unlockControlButtons();
            r4Again_feedback.setEnabled(true);
            askNext.setEnabled(true);
        } else if (game == 5 && feedback == 5) {
            lockAllButtons();
            game = 6;
            playSound(r5_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r5_game.setEnabled(true);
        } else if (game == 6 && feedback == 5) {
            lockAllButtons();
            feedback = 6;
            playSound(r5_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>Hey, you did good. <br>But it's not good enough. <br>Let's try it again.<br><br>(Press the button and go to the next round.)</html>");
            else if(mode==2)
                textLabel.setText("<html>Your score is less than a dog singing. <br>Try again.<br><br>(Press the button and go to the next round.)</html>");               
            unlockControlButtons();
            r5_feedback.setEnabled(true);
            askNext.setEnabled(true);
        } else if (game == 6 && feedback == 6) {
            lockAllButtons();
            game = 7;
            playSound(r5Again_game_wav);
            textLabel.setText("(Mimic what the robot says.)");
            unlockControlButtons();
            r5Again_game.setEnabled(true);
        } else if (game == 7 && feedback == 6) {
            lockAllButtons();
            feedback = 7;
            playSound(r5Again_feedback_wav);
            if(mode==1)
                textLabel.setText("<html>Epic play! A candy should be yours.<br><br>(Please wait for the researcher.)</html>");
            else if(mode==2)
                textLabel.setText("<html>You will never never never get the candy! <br><br>(Please wait for the researcher.)</html>");               
            unlockControlButtons();
            r5Again_feedback.setEnabled(true);
            askWait.setEnabled(true);
        } else if (game == 7 && feedback == 7) {
            lockAllButtons();
            game = 0;
            feedback = 0;
            textLabel.setText("<html>Welcome to the sound mimic game.<br>Just mimic what the robot says.<br><br>(Press the button to play the game.)</html>");
            unlockGreetButtons();
            unlockControlButtons();
            askPlay.setEnabled(true);
        }
    }//GEN-LAST:event_goNextStateActionPerformed

    private void resetGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetGameActionPerformed
        resetBox.setVisible(true);
    }//GEN-LAST:event_resetGameActionPerformed

    private void resetYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetYesActionPerformed
        resetBox.setVisible(false);
        lockAllButtons();
        clip.close();
        game = 0;
        feedback = 0;
        mode = 0;
        textLabel.setText("<html>Welcome to the sound mimic game.<br>Just mimic what the robot says.<br><br>(Press the button to play the game.)</html>");
        unlockVoiceSelectButtons();
    }//GEN-LAST:event_resetYesActionPerformed

    private void resetNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetNoActionPerformed
        resetBox.setVisible(false);
    }//GEN-LAST:event_resetNoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // Disconnect - if you miss this call the Hummingbird will continue doing stuff for five more seconds
        // you may also get a java error.
        //sillyBird.disconnect();
        clip.close();
        System.out.println("end");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(attentionBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(attentionBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(attentionBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(attentionBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new attentionBot().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton askNext;
    private javax.swing.JButton askPlay;
    private javax.swing.JButton askWait;
    private javax.swing.JLabel ask_txt;
    private javax.swing.JLabel feedback_txt;
    private javax.swing.JLabel game_txt;
    private javax.swing.JButton goNextState;
    private javax.swing.JButton greet1;
    private javax.swing.JButton greet2;
    private javax.swing.JLabel greet_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton meanFemale;
    private javax.swing.JButton meanMale;
    private javax.swing.JButton niceFemale;
    private javax.swing.JButton niceMale;
    private javax.swing.JButton r1_feedback;
    private javax.swing.JButton r1_game;
    private javax.swing.JButton r2_feedback;
    private javax.swing.JButton r2_game;
    private javax.swing.JButton r3_feedback;
    private javax.swing.JButton r3_game;
    private javax.swing.JButton r4Again_feedback;
    private javax.swing.JButton r4Again_game;
    private javax.swing.JButton r4_feedback;
    private javax.swing.JButton r4_game;
    private javax.swing.JButton r5Again_feedback;
    private javax.swing.JButton r5Again_game;
    private javax.swing.JButton r5_feedback;
    private javax.swing.JButton r5_game;
    private javax.swing.JDialog resetBox;
    private javax.swing.JButton resetGame;
    private javax.swing.JButton resetNo;
    private javax.swing.JButton resetYes;
    private javax.swing.JLabel selectVoice_txt;
    private javax.swing.JSlider servoCandy;
    private javax.swing.JLabel servoCandy_txt;
    private javax.swing.JSlider servoPan;
    private javax.swing.JLabel servoPan_txt;
    private javax.swing.JFrame textBox;
    private javax.swing.JLabel textLabel;
    // End of variables declaration//GEN-END:variables
}