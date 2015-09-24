package tutorial.basic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTirek extends ModelBase
{
  //fields
    ModelRenderer Chest;
    ModelRenderer Stomach;
    ModelRenderer Body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer Head;
    ModelRenderer HRB;
    ModelRenderer HLB;
    ModelRenderer HRT;
    ModelRenderer HLT;
    ModelRenderer ArmRight;
    ModelRenderer FistRight;
    ModelRenderer ArmLeft;
    ModelRenderer FistLeft;
  
  public ModelTirek()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      Chest = new ModelRenderer(this, 0, 140);
      Chest.addBox(-17F, -6F, 0F, 34, 11, 10);
      Chest.setRotationPoint(0F, -16F, -9F);
      Chest.setTextureSize(256, 256);
      Chest.mirror = true;
      setRotation(Chest, 0F, 0F, 0F);
      Stomach = new ModelRenderer(this, 0, 170);
      Stomach.addBox(-6F, -4F, -6F, 14, 9, 8);
      Stomach.setRotationPoint(-1F, -7F, -2F);
      Stomach.setTextureSize(256, 256);
      Stomach.mirror = true;
      setRotation(Stomach, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 190);
      Body.addBox(-6F, -10F, -7F, 12, 27, 11);
      Body.setRotationPoint(0F, 2F, 2F);
      Body.setTextureSize(256, 256);
      Body.mirror = true;
      setRotation(Body, 1.570796F, 0F, 0F);
      leg1 = new ModelRenderer(this, 0, 230);
      leg1.addBox(-3F, 0F, -2F, 4, 15, 4);
      leg1.setRotationPoint(-3F, 9F, 17F);
      leg1.setTextureSize(256, 256);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 17, 230);
      leg2.addBox(-1F, 0F, -2F, 4, 15, 4);
      leg2.setRotationPoint(3F, 9F, 17F);
      leg2.setTextureSize(256, 256);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 34, 230);
      leg3.addBox(-3F, 0F, -3F, 4, 15, 4);
      leg3.setRotationPoint(-3F, 9F, -5F);
      leg3.setTextureSize(256, 256);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 51, 230);
      leg4.addBox(-1F, 0F, -3F, 4, 15, 4);
      leg4.setRotationPoint(3F, 9F, -5F);
      leg4.setTextureSize(256, 256);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 80);
      Head.addBox(0F, 0F, 0F, 8, 8, 10);
      Head.setRotationPoint(-4F, -30F, -9F);
      Head.setTextureSize(256, 256);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      HRB = new ModelRenderer(this, 20, 70);
      HRB.addBox(0F, 0F, 0F, 4, 2, 2);
      HRB.setRotationPoint(-8F, -28F, -5F);
      HRB.setTextureSize(256, 256);
      HRB.mirror = true;
      setRotation(HRB, 0F, 0F, 0F);
      HLB = new ModelRenderer(this, 0, 70);
      HLB.addBox(0F, 0F, 0F, 4, 2, 2);
      HLB.setRotationPoint(4F, -28F, -5F);
      HLB.setTextureSize(256, 256);
      HLB.mirror = true;
      setRotation(HLB, 0F, 0F, 0F);
      HRT = new ModelRenderer(this, 10, 50);
      HRT.addBox(0F, 0F, 0F, 2, 14, 2);
      HRT.setRotationPoint(-10F, -40F, -5F);
      HRT.setTextureSize(256, 256);
      HRT.mirror = true;
      setRotation(HRT, 0F, 0F, 0F);
      HLT = new ModelRenderer(this, 0, 50);
      HLT.addBox(0F, 0F, 0F, 2, 14, 2);
      HLT.setRotationPoint(8F, -40F, -5F);
      HLT.setTextureSize(256, 256);
      HLT.mirror = true;
      setRotation(HLT, 0F, 0F, 0F);
      ArmRight = new ModelRenderer(this, 0, 120);
      ArmRight.addBox(0F, -5F, -5F, 6, 10, 8);
      ArmRight.setRotationPoint(-17F, -6F, -3F);
      ArmRight.setTextureSize(256, 256);
      ArmRight.mirror = true;
      setRotation(ArmRight, 0F, 0F, 0F);
      FistRight = new ModelRenderer(this, 0, 100);
      FistRight.addBox(0F, 0F, 0F, 8, 6, 8);
      FistRight.setRotationPoint(-18F, -1F, -8F);
      FistRight.setTextureSize(256, 256);
      FistRight.mirror = true;
      setRotation(FistRight, 0F, 0F, 0F);
      ArmLeft = new ModelRenderer(this, 35, 120);
      ArmLeft.addBox(0F, 0F, 0F, 6, 10, 8);
      ArmLeft.setRotationPoint(11F, -11F, -8F);
      ArmLeft.setTextureSize(256, 256);
      ArmLeft.mirror = true;
      setRotation(ArmLeft, 0F, 0F, 0F);
      FistLeft = new ModelRenderer(this, 35, 100);
      FistLeft.addBox(0F, 0F, 0F, 8, 6, 8);
      FistLeft.setRotationPoint(10F, -1F, -8F);
      FistLeft.setTextureSize(256, 256);
      FistLeft.mirror = true;
      setRotation(FistLeft, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Chest.render(f5);
    Stomach.render(f5);
    Body.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    Head.render(f5);
    HRB.render(f5);
    HLB.render(f5);
    HRT.render(f5);
    HLT.render(f5);
    ArmRight.render(f5);
    FistRight.render(f5);
    ArmLeft.render(f5);
    FistLeft.render(f5);
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
    float f6 = (180F / (float)Math.PI);
    this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
  }

}
