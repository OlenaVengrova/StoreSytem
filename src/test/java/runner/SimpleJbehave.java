package runner;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import com.thoughtworks.paranamer.NullParanamer;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.AbsolutePathCalculator;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;
import org.jbehave.web.selenium.*;
import org.junit.runner.RunWith;
import steps.Operations;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

@RunWith(JUnitReportingRunner.class)
public class SimpleJbehave extends JUnitStories{
//    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
//    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);
//    //private Pages pages = new Pages(driverProvider);
//    private SeleniumContext context = new SeleniumContext();
//    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
//
//    public SimpleJbehave() {
//        super();
//    }
//
//    @Override
//    public Configuration configuration() {
//        Class<? extends Embeddable> embeddableClass = this.getClass();
//        return new SeleniumConfiguration()
//                .useSeleniumContext(context)
//                .useWebDriverProvider(driverProvider)
//                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
//                .useStoryLoader(new LoadFromClasspath(embeddableClass))
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                        .withCodeLocation(codeLocationFromClass(embeddableClass))
//                        .withDefaultFormats()
//                        .withFormats(CONSOLE, HTML));
//    }

    private Configuration configuration;

    public SimpleJbehave() {
        super();
        configuration = new Configuration() {
        };

        // configuration.doDryRun(false); "no dry run" is implicit by using
        // default StoryControls

        // configuration.useDefaultStoryReporter(new ConsoleOutput());
        // deprecated -- rather use StoryReportBuilder

        configuration.useFailureStrategy(new RethrowingFailure());
        configuration.useKeywords(new LocalizedKeywords(Locale.ENGLISH));
        configuration.usePathCalculator(new AbsolutePathCalculator());
        configuration.useParameterControls(new ParameterControls());
        configuration.useParameterConverters(new ParameterConverters());
        configuration.useParanamer(new NullParanamer());
        configuration.usePendingStepStrategy(new PassingUponPendingStep());
        configuration.useStepCollector(new MarkUnmatchedStepsAsPending());
        configuration.useStepdocReporter(new PrintStreamStepdocReporter());
        configuration.useStepFinder(new StepFinder());
        configuration.useStepMonitor(new SilentStepMonitor());
        configuration
                .useStepPatternParser(new RegexPrefixCapturingPatternParser());
        configuration.useStoryControls(new StoryControls());
        configuration.useStoryLoader(new LoadFromClasspath());
        configuration.useStoryParser(new RegexStoryParser(configuration
                .keywords()));
        configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());
        configuration.useStoryReporterBuilder(new StoryReporterBuilder());
        configuration.useViewGenerator(new FreemarkerViewGenerator());

        EmbedderControls embedderControls = configuredEmbedder()
                .embedderControls();
        embedderControls.doBatch(false);
        embedderControls.doGenerateViewAfterStories(true);
        embedderControls.doIgnoreFailureInStories(false);
        embedderControls.doIgnoreFailureInView(false);
        embedderControls.doSkip(false);
        embedderControls.doVerboseFailures(false);
        embedderControls.doVerboseFiltering(false);
        embedderControls.useStoryTimeoutInSecs(300);
        embedderControls.useThreads(1);
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
            return new InstanceStepsFactory(configuration(), new Operations());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("stories\\test.story");
    }
}
