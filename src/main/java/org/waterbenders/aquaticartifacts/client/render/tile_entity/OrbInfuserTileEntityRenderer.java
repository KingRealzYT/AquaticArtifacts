package org.waterbenders.aquaticartifacts.client.render.tile_entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.waterbenders.aquaticartifacts.common.tileEntities.OrbInfuserTileEntity;

public class OrbInfuserTileEntityRenderer extends TileEntityRenderer<OrbInfuserTileEntity> {

    Minecraft mc = Minecraft.getInstance();

    public OrbInfuserTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    private int renderStateCount = 0;

    @Override
    public void render(OrbInfuserTileEntity te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        ItemStack itemStack = te.itemHandler.getStackInSlot(0);

        if (itemStack.isEmpty()) {
            return;
        }

        int lightLevel = getLightLevel(te.getLevel(), te.getBlockPos().above());

        renderItem(itemStack, new double[] { 0.5d, 1.2d, 0.5d },
                Vector3f.YP.rotationDegrees(renderStateCount), matrixStack, buffer, partialTicks,
                combinedOverlay, lightLevel, 1f);

        ITextComponent label = itemStack.hasCustomHoverName() ? itemStack.getDisplayName() : new TranslationTextComponent(itemStack.getDescriptionId());

        renderLabel(matrixStack, buffer, lightLevel, new double[] {.5d, 1.4d, .5d}, label, 0xffffff);

        renderStateCount++;
        if(renderStateCount > 360) {
            renderStateCount = 0;
        }
    }

    private void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack, IRenderTypeBuffer buffer, float partialTick, int combinedOverlay, int lightLevel, float scale) {
        matrixStack.pushPose();
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.mulPose(rotation);
        matrixStack.scale(scale, scale, scale);

        IBakedModel model = mc.getItemRenderer().getModel(stack, null, null);
        mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer,
                lightLevel, combinedOverlay, model);
        matrixStack.popPose();
    }

    private void renderLabel(MatrixStack stack, IRenderTypeBuffer buffer, int lightLevel, double[] corner, ITextComponent text, int color) {

        FontRenderer font = mc.font;

        stack.pushPose();
        float scale = 0.02f;
        int opacity = (int) (.4f * 255.0f) << 24;
        float offset = (float) (-font.width(text) / 2);
        Matrix4f matrix = stack.last().pose();

        stack.translate(corner[0], corner[1] + .4f, corner[2]);
        stack.scale(scale, scale, scale);
        stack.mulPose(mc.getEntityRenderDispatcher().cameraOrientation());
        stack.mulPose(Vector3f.ZP.rotationDegrees(180f));

        font.drawInBatch(text, offset, 0, color, false, matrix, buffer, false, opacity, lightLevel);
        stack.popPose();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getBrightness(LightType.BLOCK, pos);
        int sLight = world.getBrightness(LightType.SKY, pos);
        return LightTexture.pack(bLight, sLight);

    }
}
