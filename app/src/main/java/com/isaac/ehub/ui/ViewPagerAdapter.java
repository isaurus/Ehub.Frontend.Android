package com.isaac.ehub.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.isaac.ehub.ui.team.TeamContainerFragment;
import com.isaac.ehub.ui.team.calendar.CalendarFragment;
import com.isaac.ehub.ui.team.member.MemberFragment;
import com.isaac.ehub.ui.team.task.TaskFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull TeamContainerFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CalendarFragment();
            case 1:
                return new TaskFragment();
            case 2:
                return new MemberFragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Número de tabs
    }
}