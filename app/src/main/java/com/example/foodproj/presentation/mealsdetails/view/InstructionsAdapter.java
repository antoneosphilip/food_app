package com.example.foodproj.presentation.mealsdetails.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodproj.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.InstructionViewHolder> {

    private Context context;
    private List<String> instructionsList;

    public InstructionsAdapter(Context context, List<String> instructionsList) {
        this.context = context;
        this.instructionsList = instructionsList;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.instruction_item, parent, false);
        return new InstructionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        String instruction = instructionsList.get(position);

        holder.stepNumber.setText(String.valueOf(position + 1));

        holder.instructionText.setText(instruction);
    }

    @Override
    public int getItemCount() {
        return instructionsList != null ? instructionsList.size() : 0;
    }

    static class InstructionViewHolder extends RecyclerView.ViewHolder {
        TextView stepNumber;
        TextView instructionText;

        public InstructionViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNumber = itemView.findViewById(R.id.stepNumber);
            instructionText = itemView.findViewById(R.id.instructionText);
        }
    }
}