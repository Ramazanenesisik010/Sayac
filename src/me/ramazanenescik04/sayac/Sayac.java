package me.ramazanenescik04.sayac;

import java.awt.EventQueue;

public class Sayac {
	
	public static long currentTime = 0;
	public static long maxTime = 600000; // 10 Dakika
	
	private static SayacThread thread = null;
	
	public static boolean reverseTime = true;
	
	public static void start() {
        // Eğer thread çalışıyorsa önce durdur
        if (thread != null && thread.isRunnable) {
            stop();
        }
        
        // Yeni thread oluştur ve başlat
        if (reverseTime)
        	currentTime = maxTime + 1000 ;
        else
        	currentTime = 0;
        
        thread = new SayacThread();
        thread.start();
    }

    public static void stop() {
        if (thread != null) {
            thread.stopSayacThread();
            thread = null;
        }
    }
	
    public static void setCurrentTime(long l) {
        currentTime = l;
    }

    public static void setMAxTime(long l) {
        if (thread != null && thread.isRunnable) {
            stop();
        }
        maxTime = l;
    }

    public static boolean isStarted() {
        return thread != null && thread.isRunnable;
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SayacFrame frame = new SayacFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Long türündeki milisaniye cinsinden zamanı "00:00:00" (saat:dakika:saniye) formatına dönüştürür.
	 * 
	 * @param timeInMillis Milisaniye cinsinden zaman değeri
	 * @return "00:00:00" formatında zaman string'i
	 */
	public static String formatTime() {
	    // Milisaniyeleri saniyeye çevir
	    long totalSeconds = currentTime / 1000;
	    
	    //Dakikaları Saate, Saniyeleri dakika ve saniye olarak ayır
	    long minutes = totalSeconds / 60;
	    long seconds = totalSeconds % 60;
	    long hours = minutes / 60;
	    
	    // Üç haneli olacak şekilde formatla
	    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
	
	public static class SayacThread extends Thread {
		
		public boolean isRunnable = true;
		
		public void start() {
			this.isRunnable = true;
			super.start();
		}
		
		public void stopSayacThread() {
			this.isRunnable = false;
			this.interrupt(); // Thread'i beklemeden çıkması için interrupt et
		}
		
		public void run() {
		    long lastUpdateTime = System.currentTimeMillis();
		    
		    while(isRunnable) {
		        long currentSystemTime = System.currentTimeMillis();
		        long elapsedTime = currentSystemTime - lastUpdateTime;
		        
		        if (elapsedTime > 0) {
		        	if (reverseTime) {
		        		if (currentTime > 0) {
			                currentTime -= elapsedTime; // Geçen gerçek zamanı ekle
			            }
		        	} else {
		        		if (currentTime <= maxTime) {
			                currentTime += elapsedTime; // Geçen gerçek zamanı ekle
			            }
		        	}
		            lastUpdateTime = currentSystemTime;
		        }
		        
		        SayacFrame.text.setText(formatTime());
		        
		        try {
		            Thread.sleep(10); // 10ms'de bir güncelleme yap
		        } catch (InterruptedException e) {
		            break;
		        }
		    }
		}
		
	}

}
