package com.jwebmp.examples.apps.homepage.pages.welcome;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.SmallText;
import com.jwebmp.core.utilities.EscapeChars;
import com.jwebmp.examples.apps.homepage.components.DefaultReadMore;
import com.jwebmp.examples.apps.homepage.components.DefaultSmartWizard;
import com.jwebmp.examples.apps.homepage.components.DefaultTable;
import com.jwebmp.examples.apps.homepage.components.DisplayCard;
import com.jwebmp.examples.apps.homepage.components.browse.PluginModulePart;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.smartwizard.SmartWizardStep;
import com.jwebmp.plugins.smartwizard.SmartWizardStepItem;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.pencil_alt;

public class AboutJWebMPPage
        extends SwapScreen<AboutJWebMPPage> {

    @Override
    public PageTitle pageTitle()
    {
        return new PageTitle("Why JWebMP", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
                .addCrumb(new BreadcrumbItem("Hello World"))
                .addCrumb(new BreadcrumbItem("Why JWebMP", true))
        );
    }
    public AboutJWebMPPage() {
        add(getContentContainer());
    }

    public BSContainer<?> getContentContainer() {
        BSContainer<?> container = new BSContainer<>(BSContainerOptions.Container_Fluid);

        DivSimple<?> left = new DivSimple<>();
        DivSimple<?> right = new DivSimple<>();
        left.addClass(Col_12, Col_Md_6);
        right.addClass(Col_12, Col_Md_6);

        DivSimple<?> whatIsIt = new DivSimple<>();
        DivSimple<?> dependencies = new DivSimple<>();
        DivSimple<?> runsOnList = new DivSimple<>();
        DivSimple<?> pushMechanism = new DivSimple<>();
        DivSimple<?> testing = new DivSimple<>();
        DivSimple<?> typesafety = new DivSimple<>();

        DefaultSmartWizard wizard = new DefaultSmartWizard("aboutjwebmpwizard");

        wizard.addStep(new SmartWizardStep<>(whatIsIt, new SmartWizardStepItem<>("What Is It", new SmallText<>("About JWebMP"))));
        wizard.addStep(new SmartWizardStep<>(dependencies, new SmartWizardStepItem<>("Dependencies", new SmallText<>("A list of requirements"))));
        wizard.addStep(new SmartWizardStep<>(runsOnList, new SmartWizardStepItem<>("Runs On", new SmallText<>("Compatibility Guide"))));
        wizard.addStep(new SmartWizardStep<>(typesafety, new SmartWizardStepItem<>("Type Safe", new SmallText<>("Type Safety implementation"))));

        container.add(new BSRow<>().add(new BSColumn<>(Col_12).add(wizard)));

        BSRow<?> row = new BSRow<>();
        row.add(left);
        row.add(right);

        whatIsIt.add(row);
        left.add(buildWhyPanel());
        right.add(buildTemplateFreeDev());
        right.add(buildWhatThisIsAllAbout());
        left.add(buildHencePanel());
        left.add(buildNavigatingTheAPI());
        right.add(buildOpenSourcePanel());

        dependencies.add(buildDependencies());
        testing.add(buildTestPanel());
        runsOnList.add(buildCompatibilityList());
        typesafety.add(buildTypeSafety());
        pushMechanism.add(buildPushPanel());


        return container;
    }

    private Div buildWhyPanel() {
        DisplayCard card = new DisplayCard();
        //card.addClass("col-12 col-md-6");
        card.addCardHeader(new H3("Next Level Development").toString(true));
        Div div = card.addCardBody();
        div.add("JWebMP is a Java Web Framework that can provide any web framework available on the internet without any interference.");

        Div moreDiv = new Div();
        div.add(new DefaultReadMore(moreDiv));

        moreDiv.add("Designed to serve Modern, Efficient, and Server-Side Driven content." +
                "<br/>Built in JPMS, Develop Enterprise Scale Applications quicker than ever before, with less code than you can imagine." +
                "<br/><br/>You can seamlessly move from any JSF to JWebMP, even better,  from the straight JRE8 to JPMS in a single change");
        return card;
    }

    private Div buildTemplateFreeDev() {
        DisplayCard card = new DisplayCard();
        //	card.addClass("col-12 col-md-4");
        card.addCardHeader(new H3("Template Free Development").toString(true));
        Div div = card.addCardBody();
        //div.add(new H3<>("Template Free Development"));
        div.add("The system constructs the HTML, CSS and JavaScripts dynamically (yes according to browser and even device) to ensure that only the correct scripts get delivered. " +
                "");

        Div readMoreDiv = new Div();
        div.add(new DefaultReadMore(readMoreDiv));

        readMoreDiv.add(
                "With abstraction and injection points, you can easily manipulate any item to produce the output that you want." +
                        "<br/>Being able to render the complete output of the HTML, JavaScript and CSS of any component at any level and stage grants you an unbelievable amount of many, many benefits. Let your imagination run wild with that concept for a bit. Exactly how much coverage in your tests can you get? Want to render a component in JWebMP and push it to JSF?");
        readMoreDiv.add(
                "Of course, you can still use File Templates (though this system isn't based on it at all) with variables and scoping to continue using HTML and Web Content files to render your information. ");

        return card;
    }

    private Div buildWhatThisIsAllAbout() {
        DisplayCard card = new DisplayCard();
        card.addCardHeader(new H3<>("Do.. Whatever really").toString(0));
        Div div = card.addCardBody();
        div.add("Yeah no kidding, Integrate any of the modules to build up your application. Mix and match any items together!");

        Div more = new Div();
        div.add(new DefaultReadMore(more));

        more.add(
                "This site uses many different methods of doing the same thing across many different pages.I think a core part should not be restricting development to any particular pattern.");

        more.add(
                "There are tons of examples of the capabilities of the framework and how <i>any</i> pattern of your choice may be used in your web and mobile development, whether you are templating and importing HTMLs with specified variables or going native Java only, you're free to develop to your own preference.");
        return card;
    }

    private Div buildHencePanel() {
        DisplayCard card = new DisplayCard();
        //	card.addClass("col-12 col-md-6");
        card.addCardHeader(new H3("Only what you need").toString(true));
        Div div = card.addCardBody();
        div.add("JWebMP is 100% Modular, You can instantly port to JPMS from JRE 8");

        Div readMoreDiv = new Div();
        div.add(new DefaultReadMore(readMoreDiv));
        readMoreDiv.add("By being modular, this means that only what you add will ever get loaded, and that you are using the Module system in JDK." +
                "<br/>Develop multi-layered applications, deployable at any level with completely re-usable modules." +
                "<br/>Run REST services completely separate to JWebMP in JPMS without any concerns.");

        readMoreDiv.add("Basic injections such as <code>@Inject ReadableUserAgent</code> and many others that are always available, you can always do whatever you need to.");
        readMoreDiv.add(
                "Security is paramount, and the framework allows and caters for all forms of security integration. Local Storage for instance places a unique identifier per browser instance," +
                        "<br/>IP Locking, SAML and everything else beautiful and sexy is incredibly quick to implement.");
        return card;
    }

    private Div buildNavigatingTheAPI() {
        DisplayCard card = new DisplayCard();
        card.addCardHeader(new H3("Navigating the API").toString(0));
        Div div = card.addCardBody();
        div.add("This site is built using JWebMP, running Undertow behind an nginx proxy. The API views are reflections of the actual objects, and that way are always up to date.");

        Div more = new Div();
        div.add(new DefaultReadMore(more));

        more.add("The options browser is a simple reflection of any object. Above is the AjaxCall object that is available on every request post-load.");
        more.add("Options classes are referenced via the getOptions() method available and produce the entire API of the referenced web library");
        more.add("The toString() returns a JSON representation of that object, while toString(true) renders the HTML.");

        return card;
    }

    private Div buildOpenSourcePanel() {
        DisplayCard card = new DisplayCard();
        //card.addClass("col-12 col-md-6");
        card.addCardHeader(new H3("Open Source").toString(true));
        Div div = card.addCardBody();
        div.add("This framework is completely open source and 100% free.");

        Div moreDiv = new Div();
        div.add(new DefaultReadMore(moreDiv));
        moreDiv.add("With JIRA, and GitHub, you can log and resolve as many issues as you like, knowing that it will get fixed timelessly." +
                "<br/>There is a donate button in the top right, this is only location, I don't want to burst it out there." +
                "<br/>You will see on the plugins listings, donate buttons as well. These link to the actual developers of the web widget, " +
                "<br/>and allow you to show your support to the great developers behind everything powering the web you know and love." +
                "<br/><br/>If you like this product, please help! Pull Requests, Issue Logging, Donating, TeamCity build servers, anything. " +
                "<br/>It is always appreciated!");
        return card;
    }

    private Div buildDependencies() {
        DisplayCard card = new DisplayCard();
        Div div = card.addCardBody();
        div.add(new H3("Core Libraries"));
        DefaultTable<?> table = new DefaultTable<>();
        table.addHeader("Name", "Version", "Info", "Purpose");
        table.addRow("ClassGraph", "4.8.96", "<a href=\"https://github.com/lukehutch/fast-classpath-scanner\" target=\"_blank\">Link</a>", "Module Scanner");
        table.addRow("Google Guice", "5.0.0", "<a href=\"https://github.com/google/guice\" target=\"_blank\">Link</a>", "Injection");
        table.addRow("Guiced Injection", "${jwebmp.version}", "<a href=\"https://guicedee.com\" target=\"_blank\">Link</a>", "Injection Management");
        table.addRow("Jackson JSON", "2.12.0", "<a href=\"https://github.com/google/guice\" target=\"_blank\">Link</a>", "JSON");
        div.add(table);


        div.add(new H3("Core Web Libraries"));
        BSTable<?> webTable = new DefaultTable<>();
        ((DefaultTable) webTable).addHeader("Name", "Version", "Source", "Purpose");
        ((DefaultTable) webTable).addRow("JQuery", "3.5.1", "bower", "JS API");
        ((DefaultTable) webTable).addRow("Angular JS", "1.8.2", "bower", "Data Binder");
        ((DefaultTable) webTable).addRow("Vue JS", "2.x", "bower", "Data Binder");
        div.add(webTable);


        div.add(new H3("Servlet Libraries"));
        DefaultTable<?> servletInfoTable = new DefaultTable<>();
        servletInfoTable.addHeader("Name", "Version", "Source", "Purpose");
        servletInfoTable.addRow("UA-Detector", "0.9.22", "maven", "User Agent String");
        servletInfoTable.addRow("commons-lang3", "3.7", "maven", "StringEscapeUtils");
        servletInfoTable.addRow("commons-text", "1.4", "maven", "Text assists");
        servletInfoTable.addRow("commons-io", "12.6", "maven", "IO Management");
        div.add(servletInfoTable);

        return card;
    }

    private Div buildTestPanel() {
        DisplayCard card = new DisplayCard();
        Div div = card.addCardBody();
        div.add(new H3("Get That Coverage"));
        div.add("Test Anything, Integration and Unit Testing - Straight Out the Box!<br/> You can view all the JUnit 5 test and Selenium Integration Cases currently built and their status using the GitHub, TeamCity and SonarQube links. These allow everyone the peace of mind, and stability that end-to-end testing systems provide.<br/>");
        return card;
    }

    private Div buildCompatibilityList() {
        DisplayCard card = new DisplayCard();
        Div div = card.addCardBody();

        div.add(new H3("Doesn't Interfere"));

        div.add("You can easily run JWebMP alongside any project without causing any interference." +
                "<br/>Run next to JSF with the simple inclusion of a module, and a small faces-config update, " +
                "<br/>As a 'Add to use' platform, only modules you add are ever used, and even they can be disabled (as with this site).");

        div.add(new H3("Compatibility List"));
        DefaultTable<?> compatibilityTable = new DefaultTable<>();
        compatibilityTable.addHeader("Name", "Info", "Notes");

        compatibilityTable.addRow("Glassfish",
                "Web Fragments",
                "Including the library is enough to run JWebMP");
        compatibilityTable.addRow("Payara",
                "Web Fragments",
                "Including the library is enough to run JWebMP");
        compatibilityTable.addRow("JBoss/WildFly/EAP ",
                "Web Fragments",
                "Including the library is enough to run JWebMP");
        compatibilityTable.addRow("Tomcat",
                "Web Fragments",
                "Including the library is enough to run JWebMP");
        compatibilityTable.addRow("Wildfly Swarm",
                "Web Fragments",
                "Including the library is enough to run JWebMP");
        compatibilityTable.addRow("MicroProfile",
                "Web Fragments",
                "Including the library is enough to run JWebMP");
        compatibilityTable.addRow("Undertow",
                "Servlet Extensions",
                "You need to include the <code>jwebmp-undertow</code> module to boot.");
        compatibilityTable.addRow("Jetty",
                "Manual Initialization",
                "Call <code>Guice.inject()</code> during your boot cycle");
        compatibilityTable.addRow("Spring",
                "Guice Addon",
                "Include the <code>guice-spring</code> library in your application");
        div.add(compatibilityTable);
        return card;
    }

    private Div buildTypeSafety() {
        Div typesafety = new Div();
        typesafety.add(new H3<>("Type Safety Implementation"));
        typesafety.add("Type Safety is used everywhere, so it is better to always construct with <code>Diamond Parameters</code>" +
                "<br/>This will allow proper fluent and chaining with CRP. CRP resolves the abstract builder problem for returning the correct types." +
                "<br/>CRP (Curiously Recursive Pattern) is a full replacement for the builder pattern in JRE 8 and up and reduces class count, work required, and maintenance immensely. " +
                "<br/>I strongly recommend it - not that that counts for much?");

        typesafety.add("JWebMP Components reference the below base types for each component." +
                "<br/>It actually just turned out to spell CAFEJ on it's own. That wasn't intentionally planned.");

        typesafety.add("Features are Client-Side JavaScript that executes." +
                "<br/>Events are Server-Side driven events");

        FontAwesomeList cafej = new FontAwesomeList();
        cafej.addItem(EscapeChars.forHTML("<C> - Children - Default GlobalChildren"), FontAwesome.icon(pencil_alt));
        cafej.addItem(EscapeChars.forHTML("<A> - Attributes - Default IAttributeBase & enum"), FontAwesome.icon(pencil_alt));
        cafej.addItem(EscapeChars.forHTML("<F> - Features - Default GlobalFeatures"), FontAwesome.icon(pencil_alt));
        cafej.addItem(EscapeChars.forHTML("<E> - Events - Default GlobalEvents"), FontAwesome.icon(pencil_alt));
        cafej.addItem(EscapeChars.forHTML("<J> - Java Type (CRP) - Always the type of the object"), FontAwesome.icon(pencil_alt));
        typesafety.add(cafej);

        typesafety.add("<br/>When inheriting components, these types can be used. " +
                "<br/><br/>In certain cases, a components children will be restricted to only what that specific tag/component allows." +
                "<br/>These are restricted with interfaces that have the defaults as a parent. e.g. <code>IBSCardChildren, IBSCardBodyChildren,ISBCardHeaderChildren</code>." +
                "<br/>Extending any object and implementing the type interface will always allow placement of any tags");

        typesafety.add(
                "This greatly improves the quality of the built up HTML structures, and reduces any knowledge required with raw HTML, while still allowing complete power over any component");
        return typesafety;
    }

    private Div buildPushPanel() {
        DisplayCard card = new DisplayCard();
        Div div = card.addCardBody();
        div.add("Push and Web Sockets are standard out the box, and absolutely everything available to every Ajax Call and Response is available in the exact same coding manner.<br/><br/> " +
                "Push Groups at a Global Injection Level allow you to push responses directly from your container layer, or asynchronously from ExecutionServices in your WAR, as we do on this site.<br/><br/> You can also enable Distributed Push Notifications utilizing HazelCast or a similar technology like EhCache with ease.");
        return card;
    }
}
