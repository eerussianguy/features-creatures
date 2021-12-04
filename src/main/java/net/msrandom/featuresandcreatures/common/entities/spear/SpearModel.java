package net.msrandom.featuresandcreatures.common.entities.spear;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SpearModel extends Model {
	private final ModelRenderer pole = new ModelRenderer(32, 32, 0, 6);

	public SpearModel() {
		super(RenderType::entitySolid);
		texWidth = 32;
		texHeight = 32;

		pole.setPos(0.0F, 0.0F, 0.0F);
		pole.texOffs(0, 0).addBox(-0.5F, 1.0F, -0.5F, 1.0F, 26.0F, 1.0F, 0.01F, false);
		pole.texOffs(4, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		pole.texOffs(4, 0).addBox(-2.5F, -9.0F, 0.0F, 5.0F, 9.0F, 0.0F, 0.0F, false);
	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		pole.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}