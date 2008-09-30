// ============================================================================
//   Copyright 2006, 2007 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.watchmaker.swing;

import javax.swing.JSlider;
import org.testng.annotations.Test;
import org.uncommons.watchmaker.framework.Probability;

/**
 * Unit test for the {@link ProbabilityParameterControl} component.
 * @author Daniel Dyer
 */
public class ProbabilityParameterControlTest
{
    @Test
    public void testInitialValue()
    {
        Probability initialValue = new Probability(0.75d);
        ProbabilityParameterControl control = new ProbabilityParameterControl(initialValue);
        assert control.getNumberGenerator().nextValue().equals(initialValue) : "Wrong initial value.";
    }


    /**
     * Initial value must not be less than the minimum.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInitialValueTooLow()
    {
        new ProbabilityParameterControl(Probability.EVENS,
                                        Probability.ONE,
                                        2,
                                        new Probability(0.45)); // Should throw an IllegalArgumentException.
    }


    /**
     * Initial value must not be less than the minimum.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInitialValueTooHigh()
    {
        new ProbabilityParameterControl(Probability.ZERO,
                                        Probability.EVENS,
                                        2,
                                        new Probability(0.55)); // Should throw an IllegalArgumentException.
    }


    @Test(dependsOnMethods = "testInitialValue")
    public void testSlider()
    {
        Probability initialValue = new Probability(0.75d);
        ProbabilityParameterControl control = new ProbabilityParameterControl(initialValue);
        JSlider slider = (JSlider) control.getControl();
        assert slider.getValue() == 75 : "Wrong slider position: " + slider.getValue();
        slider.setValue(80); // 80 ticks is a probability of 0.8.
        double probability = control.getNumberGenerator().nextValue().doubleValue();
        assert probability == 0.8 : "Wrong probability: " + probability;
    }


    @Test(dependsOnMethods = "testSlider")
    public void testReset()
    {
        Probability initialValue = new Probability(0.75d);
        ProbabilityParameterControl control = new ProbabilityParameterControl(initialValue);
        JSlider slider = (JSlider) control.getControl();
        slider.setValue(80); // 80 ticks is a probability of 0.8.

        control.reset();
        assert control.getNumberGenerator().nextValue().equals(initialValue) : "NumberGenerator reset failed.";
        assert slider.getValue() == 75 : "JSlider reset failed.";
    }
}
