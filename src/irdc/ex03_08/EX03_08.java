package irdc.ex03_08;

/* import����class */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EX03_08 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* �HfindViewById()���oButton����A�å[�JonClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
    	  jumpToLayout2();
      }
    });
  }
  
  /* method jumpToLayout2�G�Nlayout��main.xml������mylayout.xml */
  public void jumpToLayout2()
  {
	/* �Nlayout�令mylayout.xml */
	setContentView(R.layout.mylayout);
	
	/* �HfindViewById()���oButton����A�å[�JonClickListener */
    Button b2 = (Button) findViewById(R.id.button2);
    b2.setOnClickListener(new Button.OnClickListener()
    {
    public void onClick(View v)
    {
    	jumpToLayout1();
    }
    });
  }
  
  /* method jumpToLayout1�G�Nlayout��mylayout.xml������main.xml */
  public void jumpToLayout1()
  {
	/* �Nlayout�令main.xml */
	setContentView(R.layout.main);
    
    /* �HfindViewById()���oButton����A�å[�JonClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    Button b3 = (Button) findViewById(R.id.button2);
    b1.setOnClickListener(new Button.OnClickListener()
    {
    public void onClick(View v)
    {
    	jumpToLayout2();
    }
    });
  }
}