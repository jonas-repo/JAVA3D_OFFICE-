/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Jonas
 */
public class Clase3d {
    
   int tamanoArreglos=12;
   public TransformGroup[] objeto3D = new TransformGroup[tamanoArreglos];
   public Transform3D[] movimientoObjeto =  new Transform3D[tamanoArreglos];
           Appearance[] apariencia = new Appearance[tamanoArreglos];
          
    
    int primitive = Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS; // coordenadas
    
    TextureLoader cargar=new TextureLoader(getClass().getResource("/probando/base1.jpg"),"INTENSITY",new Container());
    Texture textura_cilindros = cargar.getTexture();
    
    TextureLoader cargar2=new TextureLoader(getClass().getResource("/probando/lamp2.jpg"),"INTENSITY",new Container());
    Texture textura_base= cargar2.getTexture();
    
    TextureLoader cargar3=new TextureLoader(getClass().getResource("/probando/colchon2.jpg"),"INTENSITY",new Container());
    Texture texturaSilla = cargar3.getTexture();
    
    TextureLoader cargar4=new TextureLoader(getClass().getResource("/probando/lente2.jpg"),"INTENSITY",new Container());
    Texture texturaCamara = cargar4.getTexture();
    
    
    
    public void llenarArreglos()
    {   
        for (int i = 0; i < tamanoArreglos; i++) 
        {
         objeto3D[i]= new TransformGroup();
         movimientoObjeto[i]= new Transform3D();
         apariencia[i]= new Appearance();
        }
    }
   
    public  BranchGroup crearEscenaY_Lampara() 
    {
        
       llenarArreglos();
       BranchGroup lampara=new BranchGroup();
       
       Material mat=new Material();
       mat.setLightingEnable(true);
        
       TextureLoader loaderfondo =new TextureLoader(getClass().getResource("/probando/fondo2.jpg"),new Container()); // cargar fondo
       ImageComponent2D image2 = loaderfondo.getImage();
       Background fondo=new Background();
       fondo.setImage(image2);
       BoundingSphere bounds= new BoundingSphere(new Point3d(),1000.0);
       fondo.setApplicationBounds(bounds);
       lampara.addChild(fondo); 
       
      
       //Luz
       Color3f light1Color = new Color3f(255/255f,255/255f,255/255f);
       Vector3f light1Direction=new Vector3f(4.0f, -7.0f, -12.0f);
       DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
       light1.setInfluencingBounds(bounds);
       lampara.addChild(light1);
      
      //TEXTURAS 
      
      //LamparaCerca del foco
       texturas(textura_base, apariencia[0], mat);
      
      //Cilindros
       texturas(textura_cilindros, apariencia[1], mat);
       
      
     //Crear Objetos de la lampara
     //Grupo que contiene todos los objetos
     objeto3D[0]=new TransformGroup();
     permitirModificar(objeto3D[0]);
     
     //Base
     permitirModificar(objeto3D[1]);
     objeto3D[1].addChild(new Cylinder(0.08f,0.05f,primitive,apariencia[1])); // darle la figura    box(fondo,largo,ancho,primitive,textura)
     movimientoObjeto[1].setTranslation(new Vector3f(0.40f,-0.02f,0.0f));// (lugar,altura,desc)
     objeto3D[1].setTransform(movimientoObjeto[1]);
     
     //Cilindro 1
     permitirModificar(objeto3D[2]);
     objeto3D[2].addChild(new Cylinder(0.02f,0.18f,primitive,apariencia[1])); // darle la figura (ancho,largo,primitive, textura)
     movimientoObjeto[2].setTranslation(new Vector3f(0.00f,0.07f,0.0f));//(lugar,altura,desc)
     objeto3D[2].setTransform(movimientoObjeto[2]);
   
     //Esfera
     permitirModificar(objeto3D[3]);
     objeto3D[3].addChild(new Sphere(0.03f)); // darle la figura 
     movimientoObjeto[4].rotX(1);
     movimientoObjeto[3].setTranslation(new Vector3f(-0.00f,0.11f,0.0f));//(lugar,altura,desc)
     objeto3D[3].setTransform(movimientoObjeto[3]);
     
     //Cilindro2
     permitirModificar(objeto3D[4]);
     objeto3D[4].addChild(new Cylinder(0.02f,0.10f,primitive,apariencia[1])); // darle la figura (ancho,largo,primitive, textura)
     movimientoObjeto[4].setTranslation(new Vector3f(-0.01f,0.10f,0.0f));//(lugar,altura,desc)
     movimientoObjeto[4].rotX(1);
     objeto3D[4].setTransform(movimientoObjeto[4]);
     //cono
     permitirModificar(objeto3D[5]);
     objeto3D[5].addChild(new Cone(0.08f,0.10f,primitive,apariencia[0])); // darle la figura (ancho,largo,primitive, textura)
     movimientoObjeto[5].setTranslation(new Vector3f(-0.01f,-0.04f,0.0f));//(lugar,altura,desc)
     objeto3D[5].setTransform(movimientoObjeto[5]);
     
     
     //agregar objetos a pantalla nota: los objetos que se contienen al momento de moverse mueven tambien al objeto que contiene
     objeto3D[4].addChild(objeto3D[5]);
     objeto3D[3].addChild(objeto3D[4]);
     objeto3D[2].addChild(objeto3D[3]);
     objeto3D[1].addChild(objeto3D[2]);
     objeto3D[0].addChild(objeto3D[1]);
     lampara.addChild(objeto3D[0]);
     lampara.compile();
     
     return lampara;
   }
   
   public  BranchGroup crearSilla() 
   {
     llenarArreglos();
       BranchGroup silla=new BranchGroup();
       
       Material mat=new Material();
       mat.setLightingEnable(true);      
       //Luz
       Color3f light1Color = new Color3f(255/255f,255/255f,255/255f);
       Vector3f light1Direction=new Vector3f(4.0f, -7.0f, -12.0f);
       DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
       silla.addChild(light1);
      
      //****TEXTURAS****
      
      //Cilindros
       texturas(textura_cilindros, apariencia[1], mat);
      //silla 
       texturas(texturaSilla, apariencia[2], mat);
     //****************************************************  
     //objeto base
     objeto3D[0]=new TransformGroup();
     permitirModificar(objeto3D[0]);
     
     
      // cilindro base
     permitirModificar(objeto3D[6]);
     objeto3D[6].addChild(new Cylinder(0.02f,0.30f,primitive,apariencia[1])); // darle la figura cylinder(ancho,largo,primitive, textura)
     movimientoObjeto[6].setTranslation(new Vector3f(0.25f,-0.65f,0.0f));//(lugar,altura,desc)
     objeto3D[6].setTransform(movimientoObjeto[6]);
      
     // pata de abajo 1
     permitirModificar(objeto3D[7]);
     objeto3D[7].addChild(new Box(0.02f,0.01f,0.20f,primitive,apariencia[1])); //  box(fondo,largo,ancho,primitive,textura)
     movimientoObjeto[7].setTranslation(new Vector3f(0.00f,-0.15f,0.0f));//(lugar,altura,desc)
     objeto3D[7].setTransform(movimientoObjeto[7]);  
     
     // pata de abajo2
     permitirModificar(objeto3D[8]);
     objeto3D[8].addChild(new Box(0.02f,0.01f,0.20f,primitive,apariencia[1])); //  box(fondo,largo,ancho,primitive,textura)
     movimientoObjeto[8].setTranslation(new Vector3f(0.00f,-0.15f,0.0f));//(lugar,altura,desc)
     movimientoObjeto[8].rotY(2);
     objeto3D[8].setTransform(movimientoObjeto[8]);
     
     //asiento
     permitirModificar(objeto3D[9]);
     objeto3D[9].addChild(new Cylinder(0.17f,0.04f,primitive,apariencia[2])); // cylinder(ancho,largo,primitive, textura)
     movimientoObjeto[9].setTranslation(new Vector3f(0.00f,0.30f,0.0f));//(lugar,altura,desc)
     objeto3D[9].setTransform(movimientoObjeto[9]);
     
     //cilindro arriba del asiento
     permitirModificar(objeto3D[10]);
     objeto3D[10].addChild(new Cylinder(0.02f,0.17f,primitive,apariencia[1])); // darle la figura cylinder(ancho,largo,primitive, textura)
     movimientoObjeto[10].setTranslation(new Vector3f(-0.15f,0.10f,0.0f));//(lugar,altura,desc)
     objeto3D[10].setTransform(movimientoObjeto[10]);
     
     // colchon arriba del silindro del asiento
     permitirModificar(objeto3D[11]);
     objeto3D[11].addChild(new Box(0.02f,0.08f,0.17f,primitive,apariencia[2])); //  box(fondo,largo,ancho,primitive,textura)
     movimientoObjeto[11].setTranslation(new Vector3f(0.00f,0.10f,0.0f));//(lugar,altura,desc)
     objeto3D[11].setTransform(movimientoObjeto[11]);  
     
     
     
     
     
     //agregar objetos a pantalla
     objeto3D[10].addChild(objeto3D[11]);
     objeto3D[9].addChild(objeto3D[10]);
     objeto3D[8].addChild(objeto3D[9]);
     objeto3D[7].addChild(objeto3D[8]);
     objeto3D[6].addChild( objeto3D[7]);
     objeto3D[0].addChild( objeto3D[6]);
     silla.addChild(objeto3D[0]);
     silla.compile(); 
     
     
    return silla;
   }        
    
    public  BranchGroup crearCamara() //****************************************************************crear camara
    {
     llenarArreglos();
       BranchGroup camara=new BranchGroup();
       
       Material mat=new Material();
       mat.setLightingEnable(true);      
       //Luz
       Color3f light1Color = new Color3f(255/255f,255/255f,255/255f);
       Vector3f light1Direction=new Vector3f(4.0f, -7.0f, -12.0f);
       DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
       camara.addChild(light1);
       
        //TEXTURAS 
      
      //LamparaCerca del foco
       texturas(textura_base, apariencia[0], mat);
      
      //Cilindros
       texturas(textura_cilindros, apariencia[1], mat);
       texturas(texturaCamara, apariencia[2], mat);
      
     //Crear Objetos de la lampara
     //Grupo que contiene todos los objetos
     objeto3D[0]=new TransformGroup();
     permitirModificar(objeto3D[0]);
     
     //Base
     permitirModificar(objeto3D[1]);
     objeto3D[1].addChild(new Cylinder(0.04f,0.02f,primitive,apariencia[1])); // darle la figura  (ancho,largo,primitive, textura)  
     movimientoObjeto[1].rotX(2);
     movimientoObjeto[1].setTranslation(new Vector3f(0.40f,0.68f,0.0f));// (lugar,altura,desc)
     objeto3D[1].setTransform(movimientoObjeto[1]);
     
     //Cilindro 1
     permitirModificar(objeto3D[2]);
     objeto3D[2].addChild(new Cylinder(0.01f,0.18f,primitive,apariencia[1])); // darle la figura (ancho,largo,primitive, textura)
     movimientoObjeto[2].setTranslation(new Vector3f(0.00f,0.06f,0.0f));//(lugar,altura,desc)
     objeto3D[2].setTransform(movimientoObjeto[2]);
   
     //Esfera
     permitirModificar(objeto3D[3]);
     objeto3D[3].addChild(new Sphere(0.01f)); // darle la figura 
     movimientoObjeto[3].setTranslation(new Vector3f(0.00f,0.11f,0.0f));//(lugar,altura,desc)
     objeto3D[3].setTransform(movimientoObjeto[3]);
     
     //Cilindro2
     permitirModificar(objeto3D[4]);
     objeto3D[4].addChild(new Cylinder(0.01f,0.05f,primitive,apariencia[1])); // darle la figura (ancho,largo,primitive, textura)
     movimientoObjeto[4].setTranslation(new Vector3f(0.0f,0.03f,0.0f));//(lugar,altura,desc)
     objeto3D[4].setTransform(movimientoObjeto[4]);
     
     //Cilindro2
     permitirModificar(objeto3D[5]);
     objeto3D[5].addChild(new Cylinder(0.03f,0.07f,primitive,apariencia[2])); // darle la figura (ancho,largo,primitive, textura)
     movimientoObjeto[5].setTranslation(new Vector3f(0.00f,0.04f,0.0f));//(lugar,altura,desc)
     objeto3D[5].setTransform(movimientoObjeto[5]);
     
     
     objeto3D[4].addChild(objeto3D[5]); 
     objeto3D[3].addChild(objeto3D[4]);
     objeto3D[2].addChild(objeto3D[3]);
     objeto3D[1].addChild(objeto3D[2]);
     objeto3D[0].addChild(objeto3D[1]);
     camara.addChild(objeto3D[0]);
     camara.compile();
     
     
     return camara;
    
    }
    
    
    
    
    
    public void boton(Transform3D movimiento, TransformGroup figura )//rotar hacia la derecha
    {
        Transform3D aux=new Transform3D();
        double d=(Math.PI/180*5);
        aux.rotY(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
    }

    public void boton2(Transform3D movimiento, TransformGroup figura )//rotar sobre su propio eje
    {
        Transform3D aux=new Transform3D();
        double d=Math.PI/180*5;
        aux.rotZ(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
    }
    
    public void botonCam1(Transform3D movimiento, TransformGroup figura ) //mover camara hacia la izquierda
    {
        Transform3D aux=new Transform3D();
        double lim = Ventana.lim;
        if(lim<0.87) //el limite es 0.87 porque es 0.087 * 10 osea darle clic 10 veces
        {    
        double d=Math.PI/180*5;
        Ventana.lim=d+lim;//le sumamos para llevar el control del movimiento
        aux.rotZ(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
        }
    }
    
    public void botonCam2(Transform3D movimiento, TransformGroup figura ) //mover camara hacia la derecha
    {
        Transform3D aux=new Transform3D();
        double lim = Ventana.lim;
        if(lim>-0.88) //el limite es -0.87 porque es 0.087 * 10 osea darle clic 10 veces
        {    
        double d=(Math.PI/180*5)*-1;
        Ventana.lim=d+lim;//le sumamos para llevar el control del movimiento
        aux.rotZ(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
        }
    }
    
     public void botonCam3(Transform3D movimiento, TransformGroup figura ) //mover camara hacia abajo
    {
        Transform3D aux=new Transform3D();
        double lim = Ventana.lim; //jalamos la variable limite de la ventana porque sino se borra el valor que llevamos
        if(lim<0.87) //el limite es -0.87 porque es 0.087 * 10 osea darle clic 10 veces
        {    
        double d=(Math.PI/180*5);
        Ventana.lim=d+lim;//le sumamos para llevar el control del movimiento
        aux.rotX(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
        }
    }
     
    public void botonCam4(Transform3D movimiento, TransformGroup figura ) //mover camara hacia arriba
    {
        Transform3D aux=new Transform3D();
        double lim = Ventana.lim;//jalamos la variable limite de la ventana porque sino se borra el valor que llevamos
        if(lim>-0.88) //el limite es -0.87 porque es 0.087 * 10 osea darle clic 10 veces
        {    
        double d=(Math.PI/180*5)*-1;
        Ventana.lim=d+lim; //le sumamos para llevar el control del movimiento
        aux.rotX(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
        }
    }
    
    
    
    
    public void boton3(Transform3D movimiento, TransformGroup figura )// para girar hacia la derecha
    {
        Transform3D aux=new Transform3D();
        double d=(Math.PI/180*5)*-1;
        aux.rotY(d);
        movimiento.mul(aux);
        figura.setTransform(movimiento);
    }
    
    
    
    
    
    public void texturas(Texture textura, Appearance apariencia, Material mat)// todas las configuraciones necesarias para poner las texturas
    {
      textura.setBoundaryModeS(Texture.WRAP);
      textura.setBoundaryModeT(Texture.WRAP);
      textura.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
      TextureAttributes texAttr = new TextureAttributes();
      texAttr.setTextureMode(TextureAttributes.MODULATE);
      apariencia.setTexture(textura);
      apariencia.setTextureAttributes(texAttr); 
      apariencia.setMaterial(mat);
    }
    
    public void permitirModificar(TransformGroup grupoTransform)// las configuraciones que permiten modificar la figura
    {
     grupoTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
     grupoTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    }
    
    
}
