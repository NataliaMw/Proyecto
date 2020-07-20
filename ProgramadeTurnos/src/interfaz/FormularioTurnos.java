/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;
import TDAs.*;
import espol.edu.ec.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import programadeturnos.Inicio;
/**
 *
 * @author John
 */
public class FormularioTurnos extends Application{
    //Un puesto debe ponerse en estado ocupado si se va a enviar un paciente a la cita
    //Un puesto solo puede ser añadido a turno si tiene un medico encargado
    
    private PriorityQueue<Turno> turnos;
    private final AnchorPane root;
    private final ImageView logo;
    private CircularSimplyLinkedList<MediaPlayer> listaVideos;
    private MediaView vid;
    private hiloVideos hv;
    private Label turno;
    private final Label puesto;
    private Label tiempo;
    private Tiempo t;
    private Button volver;
    private Label horario;
    private int turn;
    private Label lblTurno;
    private Label lblPuesto;
        
    public FormularioTurnos(){
        turn = 0;
        turnos = new PriorityQueue<>((Turno t1, Turno t2)->Inicio.riesgos.get(t1.getPaciente().getSintoma())-Inicio.riesgos.get(t2.getPaciente().getSintoma()));
        listaVideos = cargarVideos();
        puesto = new Label("  PUESTOS  ");
        turno = new Label("  TURNOS  ");
        lblTurno = new Label("  ");
        lblPuesto = new Label("  ");
        root = new AnchorPane();
        logo = new ImageView("/recursos/imagenes/logo.png");
        volver = new Button("Volver");
        vid = new MediaView();
        organizarRoot();
    }
    
    
    private void organizarRoot(){
 
        tiempo =new Label("");
        horario =new Label("  Horario de atención: Lunes a Viernes 8h00 a 18h00 Sabados y Domingos 9h00 a 20h00");
        mostrarTiempo();
        root.setStyle("-fx-background-color: #FFFFFF");
        logo.setFitHeight(40);
        logo.setFitWidth(200);
        AnchorPane.setTopAnchor(logo, 15.0);
        AnchorPane.setLeftAnchor(logo, 15.0);
        AnchorPane.setTopAnchor(vid, 70.0);
        AnchorPane.setLeftAnchor(vid, 15.0);

        AnchorPane.setTopAnchor(puesto,70.0);
        AnchorPane.setRightAnchor(puesto, 40.0);
        AnchorPane.setTopAnchor(lblPuesto,120.0);
        AnchorPane.setRightAnchor(lblPuesto, 40.0);
        
        puesto.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-family: Arial BLACK; -fx-background-color: #1F618D");
        turno.setStyle("-fx-text-fill: WHITE; -fx-font-size: 30; -fx-font-family: Arial BLACK; -fx-background-color: #48C9B0 ");
        horario.setStyle("-fx-text-fill: WHITE; -fx-font-size: 20; -fx-font-family: Arial BLACK; -fx-background-color: #48C9B0 ");
        horario.setMinSize(1010,30);
        horario.setMaxSize(1010, 30);
        
        AnchorPane.setBottomAnchor(horario,30.0);
        AnchorPane.setTopAnchor(turno,70.0);
        AnchorPane.setRightAnchor(turno, 225.0);
        AnchorPane.setTopAnchor(tiempo,15.0);
        AnchorPane.setRightAnchor(tiempo,15.0);
        AnchorPane.setBottomAnchor(volver,5.0);
        AnchorPane.setRightAnchor(volver,5.0);
        lblPuesto.setMinSize(174.0, 50.0);
        lblPuesto.setMaxSize(174.0, 50.0);
        lblTurno.setMinSize(160.0, 50.0);
        lblTurno.setMaxSize(160.0, 50.0);
        AnchorPane.setRightAnchor(lblTurno, 225.0);
        AnchorPane.setTopAnchor(lblTurno,120.0);
        lblTurno.setStyle("-fx-text-fill: WHITE; -fx-font-size: 30; -fx-font-family: Arial BLACK; -fx-background-color: #48C9B0 ");
        lblPuesto.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-family: Arial BLACK; -fx-background-color: #1F618D");
        vid.setFitHeight(580);
        vid.setFitWidth(600);
        

        mostrarVideos();

        root.getChildren().addAll(logo,vid,puesto,turno,tiempo,volver,horario,lblPuesto,lblTurno);
        Iterator<Puesto> itPuesto =Inicio.puestos.iterator();
        Puesto sig =itPuesto.next();
        
        if(!sig.isOcupado() &sig.getMedicoA()!=null & !Inicio.pacientes.isEmpty()){
            turnos.add(new Turno(sig.getMedicoA(),Inicio.pacientes.pop(),sig,this.turnoCod()));
        }
        
        if(turnos.isEmpty()&Inicio.turno==null){
            lblTurno.setText(" ");
            lblPuesto.setText(" ");
        }
        if(Inicio.turno==null&!turnos.isEmpty()){
        Inicio.turno = turnos.poll();
        lblTurno.setText(Inicio.turno.getIdTurno());
            lblPuesto.setText(Inicio.turno.getPuesto().getIdPuesto());
        }
    
    }
 
    

    private void mostrarTiempo(){
        tiempo.setStyle("-fx-text-fill: Blue; -fx-font-size: 30; -fx-font-family: Courgette");
        t = new Tiempo();
        Thread hiloTiempo = new Thread(t);
        hiloTiempo.start();
    }
    
    private CircularSimplyLinkedList<MediaPlayer> cargarVideos(){
        CircularSimplyLinkedList<MediaPlayer> priv = new  CircularSimplyLinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/recursos/videos.txt"))){
            String nombres;
            while((nombres=br.readLine())!=null){
                String url  = Paths.get("src/recursos/videos/"+nombres).toUri().toString();
                String finals = url.replaceAll(" ", "%20"); 
                System.out.println(finals);

                Media media = new Media(finals);                
                media.setOnError(() -> System.out.println("error media"));

                MediaPlayer player = new MediaPlayer(media);
                player.setOnError(() -> System.out.println("error player"));               
                priv.addLast(player);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FormularioTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return priv;
    }
    
    
    
    public void mostrarVideos(){
        vid.setFitHeight(700);
        vid.setFitWidth(500);
        hv = new hiloVideos();
        Thread hilo = new Thread(hv);
        hilo.start();
    }
    
     public void terminarHilos(){
        hv.setEstado(false);
        t.setEstado(false);

    }
     
    class hiloVideos implements Runnable{
        private boolean estado;
        private long tiempo=187021;
        
        public hiloVideos(){
            estado = true;
        }

        public boolean isEstado() {
            return estado;
        }


        public void setEstado(boolean estado) {
            this.estado = estado;
        }

        @Override
        public void run() {
            for(MediaPlayer mp: listaVideos){
                Platform.runLater(() -> {
                        //mp.setAutoPlay(true);                        
                        vid.setMediaPlayer(mp);
                        mp.play();
                        System.out.println("LLOROOOOOO: "+String.valueOf(vid.getMediaPlayer().getTotalDuration()));
                        //tiempo = Long.valueOf(String.valueOf(vid.getMediaPlayer().getTotalDuration()));  
                        //mp.setAutoPlay(true);
                        
                    });
                try {
                    Thread.sleep(19900);
                    vid.getMediaPlayer().stop();
                } catch (InterruptedException ex) {
                    System.out.println("Errror");
                    Thread.currentThread().interrupt();
                } catch (Exception ex){
                    System.out.println("Errror");
                }
               
            if(!estado){
                break;
            }
            }
        }
        
    }
    
 
    private void crearTurnos(){
         Inicio.puestos.forEach(e->{
             
         //Turno t1 = new Turno(e.getMedicoA(),new Paciente(),e,turnoCod());
         //turnos.add(t1);
         });
         
        
    }
    public class Tiempo implements Runnable{
        
        private boolean estado;
        public Tiempo(){
            estado = true;
        }
        
        public void setEstado(boolean state){
            this.estado = state;
        }
        
        @Override
        public void run() {
            
            
            int contadorAviso = 0;
            while(estado){
                Platform.runLater(() -> {
                    
                   
                    Calendar cal = Calendar.getInstance();  
                    
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
                    Date date = cal.getTime();  
                    String timeString = formatter.format( date );  
                    tiempo.setText(timeString);         
            });
    
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FormularioTurnos.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
            }
        }
          
    }
    

    public void configBotones(Stage actual){
        volver.setOnAction(e->{
            terminarHilos();
            vid.getMediaPlayer().stop();
        PanelPrincipal pp = new PanelPrincipal();
        actual.close();
        pp.start(new Stage());});
        
    }
    
    private String turnoCod(){
        Random rd = new Random() ;
        turn = rd.nextInt(200);
        return "A"+turn;
    }
    @Override
    public void start(Stage primaryStage)  {
        configBotones(primaryStage);
        Scene scene = new Scene(root,1000,710);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Mostrar turnos");
        primaryStage.initStyle(StageStyle.DECORATED);
        //primaryStage.initStyle(StageStyle.TRANSPARENT); 
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
