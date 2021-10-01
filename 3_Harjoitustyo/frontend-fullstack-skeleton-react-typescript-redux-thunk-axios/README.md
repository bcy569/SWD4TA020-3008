# Frontend

### Ops guide

Linux Debian 10
    
    cat /etc/os-release | grep VERSION_CODENAME | cut -d = -f 2

Endpoint
<br>
http://localhost:3000

### Dev guide (Linux Debian environment setup)

"npm install --save": Since NPM 5 saving is done by default
<br>
"npm install --save-dev": Is only needed when building a node app ( --> "npm run build" will output minified JS for production) so "npm install" is enough
<br>
<br>
<br>
n (Node.js version management) and NodeJS 14.17.1
<br>
https://github.com/mklement0/n-install

    cd
    sudo apt update
    sudo apt upgrade
    wget -L https://git.io/n-install | bash
    bash n-install lts
    . ~/.bashrc
https://github.com/tj/n#installing-nodejs-versions

### Single Page Application (SPA)
<b>React</b> as SPA. Strict variable types by TypeScript. Store all React components states into Redux. Use Axios to fetch APIs

    npm install axios
    npm install @types/webpack-env
    npm audit fix

Implement React with TypeScript & Redux official template for user interface

    npx create-react-app fullstack-backbone-fe --template redux-typescript
    npm audit fix

Official template includes Redux-Toolkit which includes Redux-Thunk:
<br>
Redux-Thunk is a middleware
<br>
https://redux.js.org/faq/actions#what-async-middleware-should-i-use-how-do-you-decide-between-thunks-sagas-observables-or-something-else
<br>
for Redux-Store to handle async fetches by changing state after PENDING (will be later on --> FULFILLED or ERROR)
<br>
https://redux.js.org/usage/writing-logic-thunks
<br>
'The word "thunk" is a programming term that means "a piece of code that does some delayed work". Rather than execute some logic now, we can write a function body or code that can be used to perform the work later.'
<br>
[Redux Style Guide](https://redux.js.org/style-guide/style-guide) "Priority B" / Strongly Recommended: Use [Redux Toolkit](https://redux.js.org/style-guide/style-guide#use-action-creators) for Writing Redux Logic (try to put as much of the logic for calculating a new state into the appropriate [reducer](https://redux-toolkit.js.org/usage/usage-guide#simplifying-reducers-with-createreducer), rather than in the code that prepares and dispatches the [action](https://redux-toolkit.js.org/usage/usage-guide#defining-action-creators-with-createaction), like a click handler)
* Redux Toolkit "create[Slice](https://redux-toolkit.js.org/usage/usage-guide#creating-slices-of-state)" -[function](https://redux-toolkit.js.org/usage/usage-guide#simplifying-slices-with-createslice) will generate action creators and action types automatically

For example single file "src/features/order/orderSlice.ts" will export both orderSlice.<b>reducer</b> and orderSlice.<b>actions</b> with action types. So no more "const UPDATE_ORDER = 'UPDATE_ORDER'"

    import { createSlice } from "@reduxjs/toolkit"

    // https://redux-toolkit.js.org/usage/usage-guide#simplifying-slices-with-createslice
    const orderSlice = createSlice({
        name: 'orders',
        initialState: [],
        // CRUD
        reducers: {
        // Reducers are also action creators in "createSlice"-function
            createOrder(state, action) {}, // Creates reducer 'createOrder' and action "orders/createOrder" with type: orders/createOrder
            readOrder(state, action) {
                // TODO some code via "OrderApi"
            },
            updateOrder(state, action) {}, // Creates reducer 'updateOrder' and action "orders/updateOrder" with type: orders/updateOrder
            deleteOrder(state, action) {
                // TODO some code via "OrderApi"
            },
        },
    })
    
    // Extract and export each action creator by name 
    const { createOrder, readOrder, updateOrder, deleteOrder } = orderSlice.actions
  
    // Export the reducer
    export default orderSlice.reducer;

<br>

### Auto generate <b>A</b>pplication <b>P</b>rogramming <b>I</b>nterface (APinterface, <b>API</b>) for FrontEnd (like SPA: e.g. React) from BackEnd (e.g. java)

Download [OpenAPI Generator](https://openapi-generator.tech/docs/customization#selective-generation)

    wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.2.0/openapi-generator-cli-5.2.0.jar -O openapi-generator-cli.jar

Make sure BackEnd is up and running. Then generate DataBase / BackEnd entity-object variable type specifications for data model interface to use in React

    cd frontend
    java -jar ../openapi-generator-cli.jar generate -i http://localhost:8080/v2/api-docs?group=backbone-api -g typescript-axios -o src/api_consume_rest/vaccinationdatabase/axios

Install Axios

    npm install axios
    npm start

#### <i>ALTERNATIVE 1
Download Swagger CodeGen

    wget https://oss.sonatype.org/content/repositories/releases/io/swagger/swagger-codegen-cli/2.4.21/swagger-codegen-cli-2.4.21.jar -O swagger-codegen-cli.jar

Make sure BackEnd is up and running. Then generate DataBase / BackEnd entity-object variable type specifications for data model interface to use in React
<br>
https://swagger.io/docs/open-source-tools/swagger-codegen/
<br>
--> All code generators https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen/src/main/java/io/swagger/codegen/languages

    cd frontend
    java -jar ../swagger-codegen-cli.jar generate -i http://localhost:8080/v2/api-docs?group=backbone-api -l typescript-fetch -o src/api_consume_rest/vaccinationdatabase/fetch
    npm start
</i>

#### <i>ALTERNATIVE 2 (ReduxToolKitQuery, rtk-query)
Make sure BackEnd is up and running. Then generate DataBase / BackEnd entity-object variable type specifications for data model interface to use in React (and fetch objects)
<br>
https://redux-toolkit.js.org/rtk-query/usage/code-generation

    cd frontend
    mkdir src/api_consume_rest/vaccinationdatabase
    npx @rtk-incubator/rtk-query-codegen-openapi --hooks http://localhost:8080/v2/api-docs?group=backbone-api > src/api_consume_rest/vaccinationdatabase/redux_tool_kit_query.ts
    npm start
</i>


<br>
Inspired by https://www.mattbutton.com/redux-made-easy-with-redux-toolkit-and-typescript/
<br>
<b>In /frontend/tsconfig.json add in compilerOptions,</b>
<br>
"baseUrl": "src",
<br>
<br>
Project structure is about like this
<br>
https://redux.js.org/style-guide/style-guide/#structure-files-as-feature-folders-or-ducks

LISÄÄ TÄHÄN SCREEN SHOT TODELLISESTA OMASTA TOTEUTUKSESTANI

<br>

### Consume auto generated <b>A</b>pplication <b>P</b>rogramming <b>I</b>nterface (APinterface, <b>API</b>) on client side (such as in React)

https://jiratech.com/media/posts/integrate-openapi-specification-using-openapi-generator-to-a-reactjs-project-with-typescript-and-axios

    import { OrderApi } from 'api_consume_rest/vaccinationdatabase/axios/'
    
    const orderApi = new OrderApi()

    const newOrder = {
    ordered_ampule_bottle_id: "number of bottle",
    health_care_district: "The Hunger Games District 12",
    responsible_person: "Coriolanus Snow",
    order_number: 666,
    arrived: "1973-04-01T07:20:28.64279",
    vaccine: "No-hope",
    injections: 3,
    }

    orderApi.addOneOrderUsingPOST(newOrder);
    
    const response = orderApi.getAllOrdersUsingGET();
    
    response.then(
      // FIXME Dispatch fetched data into Redux Store
      o => console.log(o.data) 
    )

<br>
<br>
<br>
<br>
PÖÖ

    npm install react-final-form

    npm install @material-ui/core
    npm install @fontsource/roboto
    npm install @material-ui/icons

Redux is synchronous. To achieve fetch handling appropriately Redux needs asynchronous MiddleWare (professionals use [Axios](https://axios-http.com/docs/intro) instead of Fetch).
<br> 
Guru Developers uses Redux-Saga as a middleware https://redux-saga.js.org/
<br>
Normal mortals uses (redux-promises or) redux-thunk as a middleware https://www.tutorialspoint.com/redux/redux_middleware.htm


Start FrontEnd
    npm run

Install Google Chrome browser

    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
    sudo apt install ./google-chrome-stable_current_amd64.deb
    sudo apt install google-chrome-stable


Add React Developer tools -plugin for Chrome
<br>
https://reactjs.org/blog/2019/08/15/new-react-devtools.html
--> https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi

Add Redux Developer tools -plugin for Chrome
<br>
https://www.tutorialspoint.com/redux/redux_devtools.htm
--> https://chrome.google.com/webstore/detail/redux-devtools/lmhkpmbekcpmknklioeibfkpmmfibljd

Add LightHouse-plugin for Chrome
<br>
https://chrome.google.com/webstore/detail/lighthouse/blipmdconlkpinefehnmjammfjpmpbjk

Project dependencies
<br>
(./package.json)

User Interface
<br>
public/manifest.json

HTML meta
<br>
public/index.html


Tarvitaanko näitä templaten jälkeen enää?

    
    <!-- https://medium.com/swlh/interacting-with-restful-apis-using-typescript-react-hooks-and-axios-part-1-af52920ae3e4 -->
    npm install axios --save
    
    npm install redux-thunk 
    npm install -D @types/redux-thunk
    npm install redux-devtools-extension/developmentOnly
    npm install react-final-form


    npm install react-router-dom
    npm install sockjs-client
    npm run

Proxy

https://create-react-app.dev/docs/proxying-api-requests-in-development

Development test for production build

https://reactjs.org/docs/optimizing-performance.html#brunch


VisualCode

    wget -q https://packages.microsoft.com/keys/microsoft.asc -O- | sudo apt-key add -
    sudo add-apt-repository "deb [arch=amd64] https://packages.microsoft.com/repos/vscode stable main"
    sudo apt update
    sudo apt install code
    code

Material UI
<br>
https://material-ui.com/guides/typescript/