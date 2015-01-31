/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

import java.io.File;

class RunnableDemo implements Runnable {
   private Thread t;
   private Integer seconds;
   private String name;
   private Boolean stopped=false;
   private MP3 workMP3;
   private MP3 restMP3;
   RunnableDemo(String name,Integer seconds){
       this.seconds=seconds;
       this.name=name;
       if(new File(Constants.mp3Location1+"work.mp3").exists())
       {
           workMP3=new MP3(Constants.mp3Location1+"work.mp3");
           restMP3=new MP3(Constants.mp3Location1+"rest.mp3");
       }
       else if(new File(Constants.mp3Location2+"work.mp3").exists())
       {
           workMP3=new MP3(Constants.mp3Location2+"work.mp3");
           restMP3=new MP3(Constants.mp3Location2+"rest.mp3");
       }
       else if(new File(Constants.mp3Location3+"work.mp3").exists())
       {
           workMP3=new MP3(Constants.mp3Location3+"work.mp3");
           restMP3=new MP3(Constants.mp3Location3+"rest.mp3");
       }
   }
   public void run() {
      try 
      {
            //restart countdown
            if(name.contentEquals("B"))
            {
                restMP3.play();
            }
            else
            {
                workMP3.play();
            }
          for(int i=0;i<seconds; i++)
          {
              frmMain.getInstance().getLblCountdown(name).setText(String.valueOf((seconds-i)));
              Thread.sleep(1000);
              if(stopped) {break;}
          }
            frmMain.getInstance().getLblCountdown(name).setText("0");
            if(!stopped)
            {
                //restart countdown
                if(name.contentEquals("B"))
                {
                    frmMain.getInstance().start1();
                }
                else
                {
                    frmMain.getInstance().start2();
                }
            }
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
        if(name.contentEquals("B"))
        {
            restMP3.close();
        }
        else
        {
            workMP3.close();
        }
   }
}