package me.test;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {

    //程序相关
    Context context;

    //数据部分，程序无关
    Typeface futura;
    RecyclerView combatRecycler;
    RecyclerView teamRecycler;
    RecyclerView roundRecycler;
    RecyclerView scoreRecycler;
    ArrayList<Model>combatList;
    ArrayList<Model>teamList;
    ArrayList<Model>roundList;
    ArrayList<Model>scoreList;

    RootData data;

    public static StatsFragment newInstance(Bundle bundle){
        StatsFragment statsFragment=new StatsFragment();
        statsFragment.setArguments(bundle);
        return statsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        context=getActivity();
        data = (RootData) getArguments().getSerializable("data");
        super.onCreate(savedInstanceState);
    }

    public StatsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        futura = Typeface.createFromAsset(context.getAssets(), "futura medium bt.ttf");

        setupData(data);

        setupCombat(view);

        setupTeam(view);

        setupRound(view);

        setupScore(view);

        setupFont(view);

        super.onViewCreated(view, savedInstanceState);
    }

    private void setupCombat(View view){
        combatRecycler=view.findViewById(R.id.stats_combat_list);
        combatRecycler.setAdapter(new ModelAdapter(combatList,futura,R.layout.simple_list_recycler_layout));
        LinearLayoutManager manager=new LinearLayoutManager(context);
        combatRecycler.setLayoutManager(manager);
        combatRecycler.setNestedScrollingEnabled(false);
    }

    private void setupTeam(View view){

        teamRecycler=view.findViewById(R.id.stats_team_list);
        teamRecycler.setAdapter(new ModelAdapter(teamList,futura,R.layout.simple_list_recycler_layout));
        LinearLayoutManager manager=new LinearLayoutManager(context);
        teamRecycler.setLayoutManager(manager);
        teamRecycler.setNestedScrollingEnabled(false);
    }

    private void setupRound(View view){

        roundRecycler=view.findViewById(R.id.stats_round_list);
        roundRecycler.setAdapter(new ModelAdapter(roundList,futura,R.layout.simple_list_recycler_layout));
        LinearLayoutManager manager=new LinearLayoutManager(context);
        roundRecycler.setLayoutManager(manager);
        roundRecycler.setNestedScrollingEnabled(false);
    }

    private void setupScore(View view){

        scoreRecycler=view.findViewById(R.id.stats_score_list);
        scoreRecycler.setAdapter(new ModelAdapter(scoreList,futura,R.layout.simple_list_recycler_layout));
        LinearLayoutManager manager=new LinearLayoutManager(context);
        scoreRecycler.setLayoutManager(manager);
        roundRecycler.setNestedScrollingEnabled(false);
    }

    private void setupFont(View view){

        TextView combat=view.findViewById(R.id.onetime_title_combat);
        TextView team=view.findViewById(R.id.onetime_title_team);
        TextView round=view.findViewById(R.id.onetime_title_round);
        TextView score=view.findViewById(R.id.onetime_title_score);
        combat.setTypeface(futura);
        team.setTypeface(futura);
        round.setTypeface(futura);
        score.setTypeface(futura);

    }

    private void setupData(RootData data){
        MainObject mainObject=data.data;
        Aspect_Stats aspect_stats=mainObject.stats;

        setupData_Combat(aspect_stats);
        setupData_Team(aspect_stats);
        setupData_Round(aspect_stats);
        setupData_Score(aspect_stats);
    }

    private void setupData_Combat(Aspect_Stats combatStats){
        combatList=new ArrayList<>();
        combatList.add(new Model(combatStats.kdRatio.displayName,String.format("%.2f",combatStats.kdRatio.value)));
        combatList.add(new Model(combatStats.kills.displayName,String.format("%d",(int)combatStats.kills.value)));
        combatList.add(new Model(combatStats.deaths.displayName,String.format("%d",(int)combatStats.deaths.value)));
        combatList.add(new Model(combatStats.damage.displayName,String.format("%d",(int)combatStats.damage.value)));
        combatList.add(new Model(combatStats.assists.displayName,String.format("%d",(int)combatStats.assists.value)));
        combatList.add(new Model(combatStats.killsAggregated.displayName,String.format("%d",(int)combatStats.killsAggregated.value)));
        combatList.add(new Model(combatStats.assistsAsKills.displayName,String.format("%d",(int)combatStats.assistsAsKills.value)));
        combatList.add(new Model(combatStats.shotsTaken.displayName,String.format("%d",(int)combatStats.shotsTaken.value)));
        combatList.add(new Model(combatStats.shotsHit.displayName,String.format("%d",(int)combatStats.shotsHit.value)));
        combatList.add(new Model(combatStats.shotsAccuracy.displayName,String.format("%.2f",combatStats.shotsAccuracy.value)));
        combatList.add(new Model(combatStats.killStreak.displayName,String.format("%d",(int)combatStats.killStreak.value)));
        combatList.add(new Model(combatStats.dogtagsTaken.displayName,String.format("%d",(int)combatStats.dogtagsTaken.value)));
        combatList.add(new Model(combatStats.avengerKills.displayName,String.format("%d",(int)combatStats.avengerKills.value)));
        combatList.add(new Model(combatStats.saviorKills.displayName,String.format("%d",(int)combatStats.saviorKills.value)));
        combatList.add(new Model(combatStats.headshots.displayName,String.format("%d",(int)combatStats.headshots.value)));
        combatList.add(new Model(combatStats.suppressionAssists.displayName,String.format("%d",(int)combatStats.suppressionAssists.value)));
        combatList.add(new Model(combatStats.longestHeadshot.displayName,String.format("%d",(int)combatStats.longestHeadshot.value)+"m"));
        combatList.add(new Model(combatStats.killsPerMinute.displayName,String.format("%.2f",combatStats.killsPerMinute.value)));
        combatList.add(new Model(combatStats.damagePerMinute.displayName,String.format("%.2f",combatStats.damagePerMinute.value)));
    }



    private void setupData_Team(Aspect_Stats teamStats){
        teamList=new ArrayList<>();
        teamList.add(new Model(teamStats.heals.displayName,String.format("%d",(int)teamStats.heals.value)));
        teamList.add(new Model(teamStats.revives.displayName,String.format("%d",(int)teamStats.revives.value)));
        teamList.add(new Model(teamStats.revivesRecieved.displayName,String.format("%d",(int)teamStats.revivesRecieved.value)));
        teamList.add(new Model(teamStats.resupplies.displayName,String.format("%d",(int)teamStats.resupplies.value)));
        teamList.add(new Model(teamStats.aceSquad.displayName,String.format("%d",(int)teamStats.aceSquad.value)));
        teamList.add(new Model(teamStats.squadWipes.displayName,String.format("%d",(int)teamStats.squadWipes.value)));
        teamList.add(new Model(teamStats.ordersCompleted.displayName,String.format("%d",(int)teamStats.ordersCompleted.value)));
    }

    private void setupData_Round(Aspect_Stats roundStats){
        roundList=new ArrayList<>();
        roundList.add(new Model(roundStats.wlPercentage.displayName,String.format("%.2f",roundStats.wlPercentage.value)+"%"));
        roundList.add(new Model(roundStats.wins.displayName,String.format("%d",(int)roundStats.wins.value)));
        roundList.add(new Model(roundStats.losses.displayName,String.format("%d",(int)roundStats.losses.value)));
        roundList.add(new Model(roundStats.draws.displayName,String.format("%d",(int)roundStats.draws.value)));
        roundList.add(new Model(roundStats.rounds.displayName,String.format("%d",(int)roundStats.rounds.value)));
        roundList.add(new Model(roundStats.roundsPlayed.displayName,String.format("%d",(int)roundStats.roundsPlayed.value)));
    }

    private void setupData_Score(Aspect_Stats scoreStats){
        scoreList=new ArrayList<>();
        scoreList.add(new Model(scoreStats.scorePerMinute.displayName,String.format("%.2f",scoreStats.scorePerMinute.value)));
        scoreList.add(new Model(scoreStats.scoreRound.displayName,String.format("%d",(int)scoreStats.scoreRound.value)));
        scoreList.add(new Model(scoreStats.scoreGeneral.displayName,String.format("%d",(int)scoreStats.scoreGeneral.value)));
        scoreList.add(new Model(scoreStats.scoreCombat.displayName,String.format("%d",(int)scoreStats.scoreCombat.value)));
        scoreList.add(new Model(scoreStats.scoreDefensive.displayName,String.format("%d",(int)scoreStats.scoreDefensive.value)));
        scoreList.add(new Model(scoreStats.scoreObjective.displayName,String.format("%d",(int)scoreStats.scoreObjective.value)));
        scoreList.add(new Model(scoreStats.scoreBonus.displayName,String.format("%d",(int)scoreStats.scoreBonus.value)));
        scoreList.add(new Model(scoreStats.scoreSquad.displayName,String.format("%d",(int)scoreStats.scoreSquad.value)));
        scoreList.add(new Model(scoreStats.scoreAward.displayName,String.format("%d",(int)scoreStats.scoreAward.value)));
        scoreList.add(new Model(scoreStats.scoreAssault.displayName,String.format("%d",(int)scoreStats.scoreAssault.value)));
        scoreList.add(new Model(scoreStats.scoreMedic.displayName,String.format("%d",(int)scoreStats.scoreMedic.value)));
        scoreList.add(new Model(scoreStats.scoreSupport.displayName,String.format("%d",(int)scoreStats.scoreSupport.value)));
        scoreList.add(new Model(scoreStats.scoreRecon.displayName,String.format("%d",(int)scoreStats.scoreRecon.value)));
        scoreList.add(new Model(scoreStats.scoreAir.displayName,String.format("%d",(int)scoreStats.scoreAir.value)));
        scoreList.add(new Model(scoreStats.scoreLand.displayName,String.format("%d",(int)scoreStats.scoreLand.value)));
        scoreList.add(new Model(scoreStats.scoreTanks.displayName,String.format("%d",(int)scoreStats.scoreTanks.value)));
        scoreList.add(new Model(scoreStats.scoreTransports.displayName,String.format("%d",(int)scoreStats.scoreTransports.value)));
    }

}
