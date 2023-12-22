package org.hwyl.sexytopo.control.io.thirdparty.therion;

import org.hwyl.sexytopo.control.io.basic.ExportSizeCalculator;
import org.hwyl.sexytopo.model.graph.BoundingBox;
import org.hwyl.sexytopo.model.graph.Projection2D;
import org.hwyl.sexytopo.model.survey.Survey;
import org.hwyl.sexytopo.testutils.BasicTestSurveyCreator;
import org.junit.Assert;
import org.junit.Test;


public class Th2ExporterTest {

    @Test
    public void testHappyPath() {
        Survey survey = BasicTestSurveyCreator.createStraightNorth();
        float scale = TherionExporter.getScale();
        BoundingBox boundingBox = ExportSizeCalculator.getExportBoundingBox(survey, Projection2D.PLAN, scale);
        String th2 = new Th2Exporter().getContent(survey, scale, "filename.xvi", boundingBox);
        Assert.assertTrue(th2.contains("##XTHERION##"));
    }

    @Test
    public void testStripXTherion() {

        String testContent =
                "encoding utf-8\n" +
                "shouldstayhere\n" +
                "\n" +
                "\n" +
                "##XTHERION## xth_me_area_adjust -175.21 -124.65 1051.26 747.91\n" +
                "##XTHERION## zoom_to 50\n" +
                "##XTHERION## xth_me_image_insert {169.46974581605784 1 1.0} " +
                        "{566.1369842550464 1} \"NewSurvey-4 plan.xvi\" 0 {}";

        String stripped = new Th2Exporter().stripXTherion(testContent);
        Assert.assertTrue(stripped.contains("shouldstayhere"));
        Assert.assertFalse(stripped.contains("XTHERION"));
    }

}
