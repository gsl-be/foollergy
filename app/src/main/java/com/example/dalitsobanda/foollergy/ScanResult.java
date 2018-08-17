package com.example.dalitsobanda.foollergy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ScanResult extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String scan_msg = getIntent().getStringExtra("message");
        Toast.makeText(this, "Detected Text: " + scan_msg, Toast.LENGTH_LONG).show();

        TextView savemsg = (TextView) findViewById(R.id.savemsg);
        savemsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScanResult.this, MyData.class));
            }
        });

        String detections = run_detections(scan_msg);
        if (detections.equals("")){
            ((TextView)findViewById(R.id.statusmsg) ).setText("Food is Safe To Eat.");
        }else{
            ((TextView)findViewById(R.id.statusmsg) ).setText("Food is Not Safe To Eat. Contains: "+ detections);
        }
    }

    public String run_detections(String scan_msg){

            int i;
            boolean safe = true; // 1 means safe; 0 means unsafe

            String[] lactose_allergens = new String[34]; //Set the number of elements properly in ArrayList

            // Assign the array of lactose_allergents[]
            lactose_allergens[0]="Butter";
            lactose_allergens[1]="Butter fat";
            lactose_allergens[2]="Butter oil";
            lactose_allergens[3]="Butter acid";
            lactose_allergens[4]="Butter ester";
            lactose_allergens[5]="Buttermilk";
            lactose_allergens[6]="Casein";
            lactose_allergens[7]="Casein hydrolysate";
            lactose_allergens[8]="Caseinates";
            lactose_allergens[9]="Cheese";
            lactose_allergens[10]="Cottage cheese";
            lactose_allergens[11]="Cream";
            lactose_allergens[12]="curd";
            lactose_allergens[13]="Custard";
            lactose_allergens[14]="Diacetyl";
            lactose_allergens[15]="Ghee";
            lactose_allergens[16]="Half-and-half";
            lactose_allergens[17]="Lactalbumin";
            lactose_allergens[18]="Lactalbumin phosphate";
            lactose_allergens[19]="Lactoferrin";
            lactose_allergens[20]="Lactose";
            lactose_allergens[21]="Lactulose";
            lactose_allergens[22]="Milk";
            lactose_allergens[23]="Milk protein hydrolysate";
            lactose_allergens[24]="Pudding";
            lactose_allergens[25]="Recaldent";
            lactose_allergens[26]="Rennet casein";
            lactose_allergens[27]="Sour cream";
            lactose_allergens[28]="Sour cream solids";
            lactose_allergens[29]="Sour milk solids";
            lactose_allergens[30]="Tagatose";
            lactose_allergens[31]="Whey";
            lactose_allergens[32]="Whey protein hydrolysate";
            lactose_allergens[33]="Yogurt";

            String[] gluten_allergens = new String[34];

            gluten_allergens[0]="Bread crumbs";
            gluten_allergens[1]="Bulgur";
            gluten_allergens[2]="Cereal extact";
            gluten_allergens[3]="Club wheat";
            gluten_allergens[4]="Couscous";
            gluten_allergens[5]="Cracker meal";
            gluten_allergens[6]="Durum";
            gluten_allergens[7]="Einkom";
            gluten_allergens[8]="Emmer";
            gluten_allergens[9]="Farina";
            gluten_allergens[10]="Flour";
            gluten_allergens[11]="Hydrolyzed wheat protein";
            gluten_allergens[12]="Kamut";
            gluten_allergens[13]="Matzoh";
            gluten_allergens[14]="Matzoh meal";
            gluten_allergens[15]="Pasta";
            gluten_allergens[16]="Seitan";
            gluten_allergens[17]="Semolina";
            gluten_allergens[18]="Spelt";
            gluten_allergens[19]="Sprouted wheat";
            gluten_allergens[20]="Triticale";
            gluten_allergens[21]="Vital wheat gluten";
            gluten_allergens[22]="wheat";
            gluten_allergens[23]="Bran";
            gluten_allergens[24]="Gluten";
            gluten_allergens[25]="Grass";
            gluten_allergens[26]="Malt";
            gluten_allergens[27]="Sprouts";
            gluten_allergens[28]="Starch";
            gluten_allergens[29]="Wheat bran hydrolysate";
            gluten_allergens[30]="Wheat germ oil";
            gluten_allergens[31]="Wheat grass";
            gluten_allergens[32]="Wheat protein isolate";
            gluten_allergens[33]="Whole wheat berries";


            String[] egg_allergens = new String[16];

            egg_allergens[0]="Albumin";
            egg_allergens[1]="Albumen";
            egg_allergens[2]="Egg";
            egg_allergens[3]="Yolk";
            egg_allergens[4]="Eggnog";
            egg_allergens[5]="Globulin";
            egg_allergens[6]="Livetin";
            egg_allergens[7]="Lysozyme";
            egg_allergens[8]="Mayonnaise";
            egg_allergens[9]="Meringue";
            egg_allergens[10]="Meringue powder";
            egg_allergens[11]="Surimi";
            egg_allergens[12]="Vitellin";
            egg_allergens[13]="Ovo";
            egg_allergens[14]="Ova";
            egg_allergens[15]="Ovalbumin";

            String[] soy_allergens = new String[26];

            soy_allergens[0]="Edamame";
            soy_allergens[1]="Miso";
            soy_allergens[2]="Natto";
            soy_allergens[3]="Club wheat";
            soy_allergens[4]="Soy";
            soy_allergens[5]="Soy albumin";
            soy_allergens[6]="Soy cheese";
            soy_allergens[7]="Soy fiber";
            soy_allergens[8]="Soy flour";
            soy_allergens[9]="Soy grits";
            soy_allergens[10]="Soy icecream";
            soy_allergens[11]="Soy milk";
            soy_allergens[12]="Soy nuts";
            soy_allergens[13]="Soy sprouts";
            soy_allergens[14]="Soy yogurt";
            soy_allergens[15]="Soya";
            soy_allergens[16]="Soyabean";
            soy_allergens[17]="Soyabean curd";
            soy_allergens[18]="Soyabean granules";
            soy_allergens[19]="Soy protein";
            soy_allergens[20]="Shoyu";
            soy_allergens[21]="Soy sauce";
            soy_allergens[22]="Tamari";
            soy_allergens[23]="Tempeh";
            soy_allergens[24]="Textured vegetable protein";
            soy_allergens[25]="Tofu";

            String[] shellfish_allergens = new String[42];

            shellfish_allergens[0]="Barnacle";
            shellfish_allergens[1]="Crab";
            shellfish_allergens[2]="Crawfish";
            shellfish_allergens[3]="Crawdad";
            shellfish_allergens[4]="Crayfish";
            shellfish_allergens[5]="Ecrevisse";
            shellfish_allergens[6]="Krill";
            shellfish_allergens[7]="Lobster";
            shellfish_allergens[8]="Langouste";
            shellfish_allergens[9]="Langoustine";
            shellfish_allergens[10]="Moreton bug bugs";
            shellfish_allergens[11]="Scampi";
            shellfish_allergens[12]="Tomalley";
            shellfish_allergens[13]="Prawns";
            shellfish_allergens[14]="Shrimp";
            shellfish_allergens[15]="Crevette";
            shellfish_allergens[16]="Scampi";
            shellfish_allergens[17]="Abalone";
            shellfish_allergens[18]="Clams";
            shellfish_allergens[19]="Cherrystone";
            shellfish_allergens[20]="Geoduck";
            shellfish_allergens[21]="Littleneck";
            shellfish_allergens[22]="Pismo";
            shellfish_allergens[23]="Quahog";
            shellfish_allergens[24]="Cockle";
            shellfish_allergens[25]="Cuttlefish";
            shellfish_allergens[26]="Limpet";
            shellfish_allergens[27]="Lapas";
            shellfish_allergens[28]="Opihi";
            shellfish_allergens[29]="Mussels";
            shellfish_allergens[30]="Octopus";
            shellfish_allergens[31]="Oysters";
            shellfish_allergens[32]="Periwinkle";
            shellfish_allergens[33]="Scallops";
            shellfish_allergens[34]="Sea cucumber";
            shellfish_allergens[35]="Sea urchin";
            shellfish_allergens[36]="Snails";
            shellfish_allergens[37]="Escargot";
            shellfish_allergens[38]="Squid";
            shellfish_allergens[39]="Calamari";
            shellfish_allergens[40]="Whelk";
            shellfish_allergens[41]="Turban shell";


            String[] treenut_allergens = new String[41];

            treenut_allergens[0]="Almond";
            treenut_allergens[1]="Atrificial nuts";
            treenut_allergens[2]="Beechnut";
            treenut_allergens[3]="Brazil nut";
            treenut_allergens[4]="Butternut";
            treenut_allergens[5]="Cashewnut";
            treenut_allergens[6]="Chestnut";
            treenut_allergens[7]="Chinquapin nut";
            treenut_allergens[8]="Filbert";
            treenut_allergens[9]="Hazelnut";
            treenut_allergens[10]="Gianduja";
            treenut_allergens[11]="Ginkgo nut";
            treenut_allergens[12]="Hickory nut";
            treenut_allergens[13]="Litchi";
            treenut_allergens[14]="Litchee";
            treenut_allergens[15]="Lychee nut";
            treenut_allergens[16]="Macadamia nut";
            treenut_allergens[17]="Marzipan";
            treenut_allergens[18]="Almond paste";
            treenut_allergens[19]="Nangai nut";
            treenut_allergens[20]="Natural nut extract";
            treenut_allergens[21]="Nut butter";
            treenut_allergens[22]="Cashew butter";
            treenut_allergens[23]="Nut meal";
            treenut_allergens[24]="Nut meat";
            treenut_allergens[25]="Nut paste";
            treenut_allergens[26]="Nut pieces";
            treenut_allergens[27]="Pecan";
            treenut_allergens[28]="Pesto";
            treenut_allergens[29]="Pili nut";
            treenut_allergens[30]="Pine nut";
            treenut_allergens[31]="Indian nut";
            treenut_allergens[32]="Pignoli nut";
            treenut_allergens[33]="Pignolia nut";
            treenut_allergens[34]="Pignon nut";
            treenut_allergens[35]="Pifion nut";
            treenut_allergens[36]="Pinyon nut";
            treenut_allergens[37]="Pistachio";
            treenut_allergens[38]="Praline";
            treenut_allergens[39]="Sheanut";
            treenut_allergens[40]="Walnut";


            String[] peanut_allergens = new String[14];

            peanut_allergens[0]="Artificial nuts";
            peanut_allergens[1]="Beer nuts";
            peanut_allergens[2]="Cold pressed peanut oil";
            peanut_allergens[3]="Expeller pressed peanut oil";
            peanut_allergens[4]="Extruded peanut oil";
            peanut_allergens[5]="Gobbers";
            peanut_allergens[6]="Ground nuts";
            peanut_allergens[7]="Mixed nuts";
            peanut_allergens[8]="Monkey nuts";
            peanut_allergens[9]="Nut pieces";
            peanut_allergens[10]="Nut meat";
            peanut_allergens[11]="Peanut butter";
            peanut_allergens[12]="Peanut flour";
            peanut_allergens[13]="Peanut protein hydrolysate";

            String[] fish_allergens = new String[31];

            fish_allergens[0]="Barbecue sauce";
            fish_allergens[1]="Bouillabaisse";
            fish_allergens[2]="Caesar salad";
            fish_allergens[3]="Caviar";
            fish_allergens[4]="Deep fried items";
            fish_allergens[5]="Fish flavoring";
            fish_allergens[6]="Fish flour";
            fish_allergens[7]="Fish fume";
            fish_allergens[8]="Fish gelatin";
            fish_allergens[9]="Kosher gelatin";
            fish_allergens[10]="Marine gelatin";
            fish_allergens[11]="Fish oil";
            fish_allergens[12]="Fish sauce imitation fish";
            fish_allergens[13]="Shellfish isinglass";
            fish_allergens[14]="Lutefisk maw";
            fish_allergens[15]="Maws";
            fish_allergens[16]="Fish maw";
            fish_allergens[17]="Fish stock";
            fish_allergens[18]="Fishmeal";
            fish_allergens[19]="nuoc mam";
            fish_allergens[20]="Fish sauce";
            fish_allergens[21]="Anchovy ";
            fish_allergens[22]="Roe";
            fish_allergens[23]="Salad dressing";
            fish_allergens[24]="Seafood flavoring";
            fish_allergens[25]="Shark cartilage";
            fish_allergens[26]="Shark fin";
            fish_allergens[27]="Surimi";
            fish_allergens[28]="Sushi";
            fish_allergens[29]="Sashimi";
            fish_allergens[30]="Worcestershire sauce";


            String detected = "";
            //lactose
            for(i=0;i<34;i++){
                if(scan_msg.toLowerCase().contains(lactose_allergens[i].toLowerCase())){
                    System.out.println( "Lactose and milk allergen found" );
                    safe = false; // becomes unsafe here
                    detected += " " + lactose_allergens[i].toLowerCase();
                    break;
                }
            }

            //gluten
            for(i=0;i<34;i++){
                if(scan_msg.toLowerCase().contains(gluten_allergens[i].toLowerCase())){
                    System.out.println( "Gluten allergen found" );
                    safe = false; // becomes unsafe here
                    detected += " " + gluten_allergens[i].toLowerCase();
                    break;
                }
            }
            // egg
            for(i=0;i<16;i++){
                if(scan_msg.toLowerCase().contains(egg_allergens[i].toLowerCase())){
                    System.out.println( "Egg allergen found" );
                    safe = false; // becomes unsafe here
                    detected += " " + egg_allergens[i].toLowerCase();
                    break;
                }
            }
            //soy
            for(i=0;i<26;i++){
                if(scan_msg.toLowerCase().contains(soy_allergens[i].toLowerCase())){
                    System.out.println( "Soy allergen found" );
                    safe = false; // becomes unsafe here
                    detected += " " + soy_allergens[i].toLowerCase();
                    break;
                }
            }

            //shellfish
            for(i=0;i<42;i++){
                if(scan_msg.toLowerCase().contains(shellfish_allergens[i].toLowerCase())){
                    System.out.println( "Shellfish allergen found" );
                    safe = false; // becomes unsafe here
                    detected += " " + shellfish_allergens[i].toLowerCase();
                    break;
                }
            }

            //tree nut
            for(i=0;i<41;i++){
                if(scan_msg.toLowerCase().contains(treenut_allergens[i].toLowerCase())){
                    System.out.println( "Tree-nut allergen found" );
                    safe = false; // becomes unsafe here
                    detected += " " + treenut_allergens[i].toLowerCase();
                    break;
                }
            }

            //tree peanuts
            for(i=0;i<14;i++) {
                if (scan_msg.toLowerCase().contains(peanut_allergens[i].toLowerCase())) {
                    System.out.println("Peanut allergen found");
                    safe = false; // becomes unsafe here
                    detected += " " + peanut_allergens[i].toLowerCase();
                    break;
                }
            }
            //fish
            for (i = 0; i < 31; i++) {
                if (scan_msg.toLowerCase().contains(fish_allergens[i].toLowerCase())) {
                    System.out.println("Fish allergen found");
                    safe = false; // becomes unsafe here
                    detected += " " + fish_allergens[i].toLowerCase();
                    break;
                }
            }
            return detected;

        }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scan_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
