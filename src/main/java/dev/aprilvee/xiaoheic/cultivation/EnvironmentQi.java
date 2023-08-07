package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.registry.tags;
import dev.aprilvee.xiaoheic.util.xiaoheiutils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Arrays;

public class EnvironmentQi {
    static float veryhigh = 18;
    static float high = 6;
    static float moderate = 2;
    static float low = 0.25f;
    static float verylow = 0.04f;

    public static float[] getEnviroQi(BlockPos pos, Level level, int diameter){
        float[] qi = new float[7];
        float[] result;
        int radius;
        if(diameter % 2 != 0){
            radius = (diameter - 1)/2;
        }else{radius = diameter/2;}
        BlockPos starterPos = pos.offset(radius,radius,radius);
        BlockPos currentPos = starterPos;

        //level.getBlockState(currentPos).getBlock();

        for(int x = 0; x < diameter; x++){ //forward to back
            for(int y = 0; y < diameter; y++){ //top to bottom
                for(int z = 0; z < diameter; z++) { //left to right
                    result = getSpirit(currentPos, level);
                    qi[0] += result[0];//spirit
                    qi[1] += result[1];//metal
                    qi[2] += result[2];//water
                    qi[3] += result[3];//wood
                    qi[4] += result[4];//fire
                    qi[5] += result[5];//earth
                    qi[6] += result[6];//crampedness
                    currentPos = currentPos.offset(0, 0 , -1);
                    //shift block z
                }
                currentPos = currentPos.offset(0, -1, diameter);
                //shift block y
            }
            currentPos = currentPos.offset(-1, diameter, 0);
            //shift block x
        }
        return qi;
    }

    public static float[] processSpirit(float[] input, int diameter){
        //input[0] spirit, input[1-5] element, input[6] crampedness
        float[] result = {input[0], 0, 1,      0, 0, 0, 0, 0};
        //spirit, element magnitude, feng shui, element percentage
        float[] element = {input[1], input[2], input[3], input[4], input[5]};
        int volume = (int) Math.pow(diameter,3);
        float crampedness = input[6]/volume;
        result[1] = xiaoheiutils.sumArrayf(element); //magnitude

        for(int i = 0;i<5;i++){
            result[i+3] = (element[i]/result[1]);
        }

        if(crampedness >= 0.8f){
            result[0] *= 1 - crampedness/2;
            result[1] *= 1 - crampedness/2;
        }else if(crampedness >= 0.6f){
            result[0] *= 1 - crampedness/4;
            result[1] *= 1 - crampedness/4;
        }

        return result;
    }

    public static float[] getSpirit(BlockPos pos, Level level){
        //i think that i am commiting crimes
        Block block = level.getBlockState(pos).getBlock();
        Fluid fluid = level.getFluidState(pos).getType();
        float[] qi = {0, 0, 0, 0, 0, 0, 0};
        ITagManager<Block> tag = ForgeRegistries.BLOCKS.tags();
        //ITagManager<Fluid> fluidtag = ForgeRegistries.FLUIDS.tags();

        if(block != Blocks.AIR && fluid != Fluids.EMPTY){
            if(tag.getTag(tags.spiritvstrong).contains(block)){ qi[0] = veryhigh;
            } else if(tag.getTag(tags.spiritstrong).contains(block)){ qi[0] = high;
            } else if(tag.getTag(tags.spirit).contains(block)){ qi[0] = moderate;
            } else if(tag.getTag(tags.spiritweak).contains(block)){ qi[0] = low;
            }

            if(tag.getTag(tags.metalstrong).contains(block)){ qi[1] = high;
            } else if(tag.getTag(tags.metal).contains(block)){ qi[1] = moderate;
            } else if(tag.getTag(tags.metalweak).contains(block)){ qi[1] = low;
            }

            if(tag.getTag(tags.waterstrong).contains(block)){qi[2] = high;
            } else if(tag.getTag(tags.water).contains(block)){qi[2] = moderate;
            } else if(tag.getTag(tags.waterweak).contains(block)){qi[2] = low;
            }

            if(tag.getTag(tags.woodstrong).contains(block)){qi[3] = high;
            } else if(tag.getTag(tags.wood).contains(block)){qi[3] = moderate;
            } else if(tag.getTag(tags.woodweak).contains(block)){qi[3] = low;
            }

            if(tag.getTag(tags.firestrong).contains(block)){qi[4] = high;
            } else if(tag.getTag(tags.fire).contains(block)){qi[4] = moderate;
            } else if(tag.getTag(tags.fireweak).contains(block)){qi[4] = low;
            }

            if(tag.getTag(tags.earthstrong).contains(block)){qi[5] = high;
            } else if(tag.getTag(tags.earth).contains(block)){qi[5] = moderate;
            } else if(tag.getTag(tags.earthweak).contains(block)){qi[5] = low;
            } else if(tag.getTag(tags.earthveryweak).contains(block)){qi[5] = verylow;
            }

            if(fluid == Fluids.WATER) {qi[2] += low;
            }else if(fluid == Fluids.LAVA){qi[4] += moderate;
            }

            if(tag.getTag(tags.solid).contains(block)){qi[6] = 1;} //crampedness
        }


        return qi;
    }
}