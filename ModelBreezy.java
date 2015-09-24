package tutorial.basic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBreezy extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer BL;
    ModelRenderer FR;
    ModelRenderer FL;
    ModelRenderer BR;
    ModelRenderer Neck;
    ModelRenderer Body;
    ModelRenderer Mouth;
    ModelRenderer EarLeft;
    ModelRenderer EarRight;
    ModelRenderer AntenaLeft;
    ModelRenderer AntenaRight;
    ModelRenderer BallLeft;
    ModelRenderer BallRight;
    ModelRenderer WBL;
    ModelRenderer WBR;
    ModelRenderer WML;
    ModelRenderer WMR;
    ModelRenderer WTL;
    ModelRenderer WTR;
  
  public ModelBreezy()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Head = new ModelRenderer(this, 100, 100);
      Head.addBox(0F, 0F, 0F, 4, 4, 4);
      Head.setRotationPoint(-2F, -0.5F, -6F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      BL = new ModelRenderer(this, 100, 0);
      BL.addBox(0F, 0F, 0F, 1, 15, 1);
      BL.setRotationPoint(1F, 9F, 2F);
      BL.setTextureSize(128, 128);
      BL.mirror = true;
      setRotation(BL, 0.2268928F, 0F, 0F);
      FR = new ModelRenderer(this, 100, 0);
      FR.addBox(0F, 0F, 0F, 1, 15, 1);
      FR.setRotationPoint(-2F, 9F, -4F);
      FR.setTextureSize(128, 128);
      FR.mirror = true;
      setRotation(FR, 0.2268928F, 0F, 0F);
      FL = new ModelRenderer(this, 100, 0);
      FL.addBox(0F, 0F, 0F, 1, 15, 1);
      FL.setRotationPoint(1F, 9F, -4F);
      FL.setTextureSize(128, 128);
      FL.mirror = true;
      setRotation(FL, 0.2268928F, 0F, 0F);
      BR = new ModelRenderer(this, 100, 0);
      BR.addBox(0F, 0F, 0F, 1, 15, 1);
      BR.setRotationPoint(-2F, 9F, 2F);
      BR.setTextureSize(128, 128);
      BR.mirror = true;
      setRotation(BR, 0.2268928F, 0F, 0F);
      Neck = new ModelRenderer(this, 100, 90);
      Neck.addBox(0F, 0F, 0F, 2, 4, 2);
      Neck.setRotationPoint(-1F, 3F, -5F);
      Neck.setTextureSize(128, 128);
      Neck.mirror = true;
      setRotation(Neck, 0.2617994F, 0F, 0F);
      Body = new ModelRenderer(this, 32, 16);
      Body.addBox(0F, 0F, 0F, 4, 4, 8);
      Body.setRotationPoint(-2F, 6F, -4F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Mouth = new ModelRenderer(this, 4, 100);
      Mouth.addBox(0F, 0F, 0F, 1, 1, 1);
      Mouth.setRotationPoint(-0.5F, 2F, -6.5F);
      Mouth.setTextureSize(128, 128);
      Mouth.mirror = true;
      setRotation(Mouth, 0F, 0F, 0F);
      EarLeft = new ModelRenderer(this, 10, 100);
      EarLeft.addBox(0F, 0F, 0F, 0, 2, 1);
      EarLeft.setRotationPoint(2F, -2F, -4.5F);
      EarLeft.setTextureSize(128, 128);
      EarLeft.mirror = true;
      setRotation(EarLeft, 0F, 0F, 0.1396263F);
      EarRight = new ModelRenderer(this, 15, 100);
      EarRight.addBox(0F, 0F, 0F, 0, 2, 1);
      EarRight.setRotationPoint(-2F, -2F, -4.5F);
      EarRight.setTextureSize(128, 128);
      EarRight.mirror = true;
      setRotation(EarRight, 0F, 0F, -0.1396263F);
      AntenaLeft = new ModelRenderer(this, 50, 50);
      AntenaLeft.addBox(0F, -11F, 0F, 1, 11, 1);
      AntenaLeft.setRotationPoint(1F, 0F, -6F);
      AntenaLeft.setTextureSize(128, 128);
      AntenaLeft.mirror = true;
      setRotation(AntenaLeft, 0.296706F, 0F, 0.2094395F);
      AntenaRight = new ModelRenderer(this, 50, 50);
      AntenaRight.addBox(0F, -11F, 0F, 1, 11, 1);
      AntenaRight.setRotationPoint(-2F, 0F, -6F);
      AntenaRight.setTextureSize(128, 128);
      AntenaRight.mirror = true;
      setRotation(AntenaRight, 0.296706F, 0F, -0.2094395F);
      BallLeft = new ModelRenderer(this, 50, 70);
      BallLeft.addBox(0F, 0F, 0F, 2, 2, 2);
      BallLeft.setRotationPoint(3F, -12F, -10F);
      BallLeft.setTextureSize(128, 128);
      BallLeft.mirror = true;
      setRotation(BallLeft, 0F, 0F, 0F);
      BallRight = new ModelRenderer(this, 50, 70);
      BallRight.addBox(0F, 0F, 0F, 2, 2, 2);
      BallRight.setRotationPoint(-5F, -12F, -10F);
      BallRight.setTextureSize(128, 128);
      BallRight.mirror = true;
      setRotation(BallRight, 0F, 0F, 0F);
      WBL = new ModelRenderer(this, 1, 20);
      WBL.addBox(0F, 0F, 0F, 10, 0, 3);
      WBL.setRotationPoint(1F, 6F, 0F);
      WBL.setTextureSize(128, 128);
      WBL.mirror = true;
      setRotation(WBL, 0F, 0F, 0.5235988F);
      WBR = new ModelRenderer(this, 1, 20);
      WBR.addBox(-10F, 0F, 0F, 10, 0, 3);
      WBR.setRotationPoint(-1F, 6F, 0F);
      WBR.setTextureSize(128, 128);
      WBR.mirror = true;
      setRotation(WBR, 0F, 0F, -0.5235988F);
      WML = new ModelRenderer(this, 1, 20);
      WML.addBox(0F, 0F, 0F, 10, 0, 3);
      WML.setRotationPoint(1F, 6F, 0F);
      WML.setTextureSize(128, 128);
      WML.mirror = true;
      setRotation(WML, 0F, 0F, 0F);
      WMR = new ModelRenderer(this, 1, 20);
      WMR.addBox(-10F, 0F, 0F, 10, 0, 3);
      WMR.setRotationPoint(-1F, 6F, 0F);
      WMR.setTextureSize(128, 128);
      WMR.mirror = true;
      setRotation(WMR, 0F, 0F, 0F);
      WTL = new ModelRenderer(this, 1, 20);
      WTL.addBox(0F, 0F, 0F, 10, 0, 3);
      WTL.setRotationPoint(1F, 6F, 0F);
      WTL.setTextureSize(128, 128);
      WTL.mirror = true;
      setRotation(WTL, 0F, 0F, -0.5235988F);
      WTR = new ModelRenderer(this, 1, 20);
      WTR.addBox(-10F, 0F, 0F, 10, 0, 3);
      WTR.setRotationPoint(-1F, 6F, 0F);
      WTR.setTextureSize(128, 128);
      WTR.mirror = true;
      setRotation(WTR, 0F, 0F, 0.5235988F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    BL.render(f5);
    FR.render(f5);
    FL.render(f5);
    BR.render(f5);
    Neck.render(f5);
    Body.render(f5);
    Mouth.render(f5);
    EarLeft.render(f5);
    EarRight.render(f5);
    AntenaLeft.render(f5);
    AntenaRight.render(f5);
    BallLeft.render(f5);
    BallRight.render(f5);
    WBL.render(f5);
    WBR.render(f5);
    WML.render(f5);
    WMR.render(f5);
    WTL.render(f5);
    WTR.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
