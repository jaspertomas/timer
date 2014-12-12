/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

class RunnableDemo implements Runnable {
   private Thread t;
   private Integer seconds;
   private String name;
   private Boolean stopped=false;
   RunnableDemo(String name,Integer seconds){
       this.seconds=seconds;
       this.name=name;
   }
   public void run() {
      try 
      {
          for(int i=0;i<seconds; i++)
          {
              frmMain.getInstance().getLblCountdown(name).setText(String.valueOf((seconds-i)));
              Thread.sleep(1000);
              if(stopped) {break;}
          }
            frmMain.getInstance().getLblCountdown(name).setText("0");
            if(!stopped)mp3.play();
      } catch (InterruptedException e) {}
   }
   
   public void start ()
   {
      if (t == null)
      {
         t = new Thread (this);
         t.start ();
      }
   }
   public void stop ()
   {
       stopped=true;
       mp3.close();
   }
    MP3 mp3=new MP3("alarm.mp3");
}