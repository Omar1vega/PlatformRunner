package vegao1.onyxassignment4;

import android.graphics.Bitmap;
import android.graphics.Rect;


public class Ground extends OxBlock {
    public Ground (BitmapWrapper img, int x, int y){
        image=img.getImage();
        super.PRINTSIZE = 100;
        rectangle = new Rect();
        setRectangle(x,y);
        live = true;
    }
    public Ground (){
        super.PRINTSIZE = 100;
    }
    public int getPRINTSIZE(){return PRINTSIZE;}
}
